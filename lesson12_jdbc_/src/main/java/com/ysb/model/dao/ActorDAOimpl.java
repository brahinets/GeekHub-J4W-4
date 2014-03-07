package com.ysb.model.dao;

import com.ysb.model.entities.Actor;
import com.ysb.model.entities.Film;
import com.ysb.model.service.FilmService;
import com.ysb.model.utils.ConnectionUtils;
import com.ysb.model.utils.EntityUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of ActorDAO that uses database as a storage for objects.
 * It uses reflection to access objects fields and retrieve data to map to database tables.
 * As an identifier it uses field id of Entity class.
 */
public class ActorDAOimpl implements ActorDAO{
    private Connection connection = ConnectionUtils.getConnection();


    public Set<Film> getFilmsOfActor(Integer actorId) throws Exception {
        String sqlActorsList = "SELECT filmId FROM ACTOR_FILM WHERE actorId = " + actorId;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sqlActorsList);
        Set<Film> filmsList = new HashSet<>();

        while (rs.next()) {
            filmsList.add(FilmService.getFilmByID(rs.getInt("filmId")));
        }

        return filmsList;
    }


    public Actor getByID(Integer id) throws Exception {
        String sqlActor = "SELECT * FROM ACTOR WHERE id = " + id;
        Statement statement = connection.createStatement();
        List<Actor> queryResult = EntityUtils.extractResult(Actor.class, statement.executeQuery(sqlActor));

        if(!queryResult.isEmpty()){
            return queryResult.get(0);
        }

        return null;
    }


    public List<Actor> getAll() throws Exception {
        String sql = "SELECT * FROM ACTOR";
        try(Statement statement = connection.createStatement()) {
            return EntityUtils.extractResult(Actor.class, statement.executeQuery(sql));
        }
    }


    public void delete(Actor entity) throws Exception {
        String fromActor = "DELETE FROM ACTOR WHERE id = " + entity.getId();
        String fromActorActor = "DELETE FROM ACTOR_FILM WHERE actorId = " + entity.getId();
        PreparedStatement statement = connection.prepareStatement(fromActor);
        statement.execute();
        statement = connection.prepareStatement(fromActorActor);
        statement.execute();
    }


    public void save(Actor entity) throws Exception {
        Map<String, Object> data = EntityUtils.prepareEntity(entity);
        StringBuilder sql = new StringBuilder("");
        StringBuilder keys = new StringBuilder("");
        StringBuilder values = new StringBuilder("");
        StringBuilder sql1;

        if (entity.isNew()) {
            sql.append("INSERT INTO ACTOR ( ");
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

        try (Statement statement = connection.createStatement()){
            sql1 = new StringBuilder("DELETE FROM ACTOR_FILM WHERE actorId = ?");
            statement.execute(sql1.toString(), entity.getId());

            for(Film film : ((Actor) entity).getFilms()){
                sql1 = new StringBuilder("INSERT INTO ACTOR_FILM ( actorId, filmId) VALUES ('" + entity.getId() + "', '" + film.getId() + "')");
                statement.execute(sql1.toString());
            }
        }
    }


    public Integer getFilmsCount(Actor actor) throws Exception {
        String sql = "SELECT COUNT(*) AS count FROM ACTOR_FILM WHERE actorId = " + actor.getId();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        Integer count = rs.getInt("count");
        rs.close();

        return count;
    }
}