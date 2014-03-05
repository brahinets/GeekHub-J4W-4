package com.ysb.model.dao;

import com.ysb.model.entities.Actor;
import com.ysb.model.entities.Film;
import com.ysb.model.service.ActorService;
import com.ysb.model.utils.ConnectionUtils;
import com.ysb.model.utils.EntityUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

/**
 * Implementation of StorageDAO for Film class  that uses database as a storage for objects.
 * It uses reflection to access objects fields and retrieve data to map to database tables.
 * As an identifier it uses field id of Entity class.
 */
public class FilmDAOimpl implements FilmDAO{
    private Connection connection = ConnectionUtils.getConnection();


    public Set<Actor> getActorsToFilm(Integer filmId) throws Exception {
        String sqlActorsList = "SELECT actorId FROM ACTOR_FILM WHERE filmId = " + filmId;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sqlActorsList);
        Set<Actor> actorsList = new HashSet<>();

        while (rs.next()) {
            actorsList.add(ActorService.getActorByID(rs.getInt("actorId")));
        }

        return actorsList;
    }


    public Film getByID(Integer id) throws Exception {
        String sqlFilm = "SELECT * FROM FILM WHERE id = " + id;
        Statement statement = connection.createStatement();
        List<Film> queryResult = EntityUtils.extractResult(Film.class, statement.executeQuery(sqlFilm));

        if(!queryResult.isEmpty()){
            return queryResult.get(0);
        }

        return null;
    }


    public List<Film> getAll() throws Exception {
        String sql = "SELECT * FROM FILM";
        try(Statement statement = connection.createStatement()) {
            return EntityUtils.extractResult(Film.class, statement.executeQuery(sql));
        }
    }


    public void delete(Film entity) throws Exception {
        String fromFilm = "DELETE FROM FILM WHERE id = " + entity.getId();
        String fromActorFilm = "DELETE FROM ACTOR_FILM WHERE filmId = " + entity.getId();
        PreparedStatement statement = connection.prepareStatement(fromFilm);
        statement.execute();
        statement = connection.prepareStatement(fromActorFilm);
        statement.execute();
    }


    public void save(Film entity) throws Exception {
        Map<String, Object> data = EntityUtils.prepareEntity(entity);
        StringBuilder sql = new StringBuilder("");
        StringBuilder keys = new StringBuilder("");
        StringBuilder values = new StringBuilder("");

        if (entity.isNew()) {
            sql.append("INSERT INTO FILM ( ");
            for(String key:data.keySet()) {
                keys.append(key).append(",");
                values.append("'").append(data.get(key)).append("'").append(",");
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
                    sql.append(key).append(" = '").append(data.get(key)).append("' ,");
                }
                sql.deleteCharAt(sql.length() - 1).append(" WHERE id = '" + entity.getId()+ "'");
                statement.execute(sql.toString());
            }
        }
    }

}