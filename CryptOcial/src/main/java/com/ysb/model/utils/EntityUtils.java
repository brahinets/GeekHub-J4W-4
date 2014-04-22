package com.ysb.model.utils;

import com.ysb.model.entity.Entity;
import com.ysb.model.entity.Ignore;
import com.ysb.model.entity.User;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EntityUtils {

    /* converts object to map, could be helpful in save method */
    public static <T extends Entity> Map<String, Object> prepareEntity(T entity) throws Exception {
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


    /* converts result of sql query to List of objects */
    public static <T extends Entity> List<T> extractResult(Class<T> clazz, ResultSet resultset) throws Exception {
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


    public static boolean isNull(Object object){
        return object == null;
    }


    /* converts result of sql query to User object */
    /*public static User extractUserShort(ResultSet rs) throws Exception {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setCurrentAvatar(rs.getBytes("currentAvatar"));
        user.setIsOnline(rs.getBoolean("isOnline"));
        user.setWhereFrom(rs.getString("whereFrom"));

        return user;
    }*/
}