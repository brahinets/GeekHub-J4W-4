/**
 * Class: DatabaseStorage
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 30.11.13
 * Time: 18:23
 * Mail: ysb.kanivtsi@gmail.com
 */

package org.geekhub.storage;

import org.geekhub.objects.Entity;
import org.geekhub.objects.Ignore;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link Storage} that uses database as a storage for objects.
 * It uses simple object type names to define target table to save the object.
 * It uses reflection to access objects fields and retrieve data to map to database tables.
 * As an identifier it uses field id of {@link org.geekhub.objects.Entity} class.
 * Could be created only with {@link java.sql.Connection} specified.
 */
public class DatabaseStorage implements Storage {
    private Connection connection;

    public DatabaseStorage(Connection connection) {
        this.connection = connection;
    }


    /* return object with some id */
    @Override
    public <T extends Entity> T get(Class<T> clazz, Integer id) throws Exception {
        //this method is fully implemented, no need to do anything, it's just an example
        String sql = "SELECT * FROM " + clazz.getSimpleName() + " WHERE id = " + id;
        try(Statement statement = connection.createStatement()) {
            List<T> result = extractResult(clazz, statement.executeQuery(sql));
            return result.isEmpty() ? null : result.get(0);
        }
    }



    /* create list of objects (get all rows as objects from some table) */
    @Override
    public <T extends Entity> List<T> list(Class<T> clazz) throws Exception {
        String sql = "SELECT * FROM " + clazz.getSimpleName();
        try(Statement statement = connection.createStatement()) {
            return extractResult(clazz, statement.executeQuery(sql));
        }
    }


    /* DELETE object from DataBase */
    @Override
    public <T extends Entity> boolean delete(T entity) throws Exception {
        String sql = "DELETE FROM " + entity.getClass().getSimpleName() + " WHERE id = " + entity.getId();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            return statement.execute();
        }
    }


    /* INSERT object to DataBase, or UPDATE if already exists */
    @Override
    public <T extends Entity> void save(T entity) throws Exception {
        Map<String, Object> data = prepareEntity(entity);
        StringBuilder sql = new StringBuilder("");
        StringBuilder keys = new StringBuilder("");
        StringBuilder values = new StringBuilder("");

        if (entity.isNew()) {
            sql.append("INSERT INTO " + entity.getClass().getSimpleName() + " ( ");
            for(String key:data.keySet()) {
                keys.append(key).append(",");
                if(data.get(key) instanceof Boolean){
                    values.append(((Boolean)data.get(key)) ? "'1', " : "'0', ");
                }  else {
                    values.append("'").append(data.get(key)).append("'").append(",");
                }
            }
            sql.append(keys.deleteCharAt(keys.length() - 1) + ") VALUES (" + values.deleteCharAt(values.length() - 1) + ")");
            try(Statement statement = connection.createStatement()) {
                statement.execute(sql.toString(), Statement.RETURN_GENERATED_KEYS);
                ResultSet generatedKeys = statement.getGeneratedKeys();
                generatedKeys.next();
                entity.setId(generatedKeys.getInt(1));
            }
        } else {
            try(Statement statement = connection.createStatement()) {
                sql.append("UPDATE " + entity.getClass().getSimpleName() + " SET ");
                for(String key:data.keySet()) {
                    if(data.get(key) instanceof Boolean){
                        sql.append(key).append(((Boolean)data.get(key)) ? " = '1'," : " = '0',");
                    }  else {
                        sql.append(key).append(" = '").append(data.get(key)).append("' ,");
                    }
                }
                sql.deleteCharAt(sql.length() - 1).append(" WHERE id = '" + entity.getId()+ "'");
                statement.execute(sql.toString());
            }
        }
    }


    /* converts object to map, could be helpful in save method */
    private <T extends Entity> Map<String, Object> prepareEntity(T entity) throws Exception {
        Map<String, Object> data = new HashMap<>();
        Field[] fields = entity.getClass().getDeclaredFields();
        AccessibleObject.setAccessible(fields, true);
        for (Field f: fields){
            if(!f.isAnnotationPresent(Ignore.class)){ /* if field of the object has not ignore annotation -> then skip this field */
                data.put(f.getName() , f.get(entity));
            }
        }
        AccessibleObject.setAccessible(fields,false);

        return data;
    }


    /* creates list of new instances of clazz by using data from result set */
    private <T extends Entity> List<T> extractResult(Class<T> clazz, ResultSet resultset) throws Exception {
        List<T> result = new LinkedList<>();
        Field[] fields = clazz.getDeclaredFields();
        T instance;

        AccessibleObject.setAccessible(fields, true);
        while(resultset.next()) {
            instance = clazz.newInstance();
            for (Field f: fields){
                if(!f.isAnnotationPresent(Ignore.class)){ /* if field of object has not ignore annotation -> then work with this field */
                    f.set(instance, resultset.getObject(f.getName()));
                }
            }
            instance.setId((Integer) resultset.getObject("id"));
            result.add(instance);
        }
        AccessibleObject.setAccessible(fields, false);

        return result;
    }
}