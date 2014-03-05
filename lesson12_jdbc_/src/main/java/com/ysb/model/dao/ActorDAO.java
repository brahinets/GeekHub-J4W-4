package com.ysb.model.dao;

import com.ysb.model.entities.Actor;
import com.ysb.model.entities.Film;

import java.util.List;
import java.util.Set;

/**
 * Implementation of ActorDAO that uses database as a storage for objects.
 * It uses reflection to access objects fields and retrieve data to map to database tables.
 * As an identifier it uses field id of Entity class.
 */
public interface ActorDAO {

    /**
     * Retrieves List of Films in which played actor with specified ID from storage
     *
     * @param actorId identifier of actor.
     * @return list os new instances of Film or null if it is not find nothing
     * @throws Exception
     */
    public Set<Film> getFilmsOfActor(Integer actorId) throws Exception;


    /**
     * Retrieves Film from storage by its identifier.
     *
     * @param id identifier of that object.
     * @return new instance of Film or null if it is not find by id.
     * @throws Exception
     */
    public Actor getByID(Integer id) throws Exception;


    /**
     * Retrieves all Actors from storage.
     * @return getAll of objects of Actor or empty getAll if storage does not contains any object.
     * @throws Exception
     */
    public List<Actor> getAll() throws Exception;


    /**
     * Deletes actor from storage.
     * @param entity that will be deleted
     * @throws Exception
     */
    public void delete(Actor entity) throws Exception;


    /**
     * If Actors does not exists in storage yet it will be created, otherwise it will be updated.
     * @param entity that will be stored in storage.
     * @throws Exception
     */
    public void save(Actor entity) throws Exception;

}