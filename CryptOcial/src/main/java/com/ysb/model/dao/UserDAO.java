package com.ysb.model.dao;

import com.ysb.model.entity.User;

/**
 * Implementation of userDAO that uses database as a storage for objects.
 * It uses reflection to access objects fields and retrieve data to map to database tables.
 * As an identifier it uses field id of Entity class.
 */
public interface UserDAO {

//    /**
//     * Retrieves List of Groups in which user with some ID exists
//     *
//     * @param actorId identifier of actor.
//     * @return list os new instances of Film or null if it is not find nothing
//     * @throws Exception
//     */
//    public Set<Group> getGroupsOfActor(Integer actorId) throws Exception;


    /**
     * Retrieves User from storage by its identifier.
     *
     * @param id identifier of that object.
     * @return new instance of User or null if it is not find by id.
     * @throws Exception
     */
    public User getUser(Integer id) throws Exception;


    /**
     * Retrieves User from storage by its LOGIN ans PASSWORD.
     *
     * @param login getUserID of user
     * @param password password of user
     * @return new instance of User or null if it is not find by id.
     * @throws Exception
     */
//    public User getUser(String login, String password) throws Exception;


//    /**
//     * Retrieves all Actors from storage.
//     * @return list of bjects of Actor or empty list if storage does not contains any object.
//     * @throws Exception
//     */
//    public List<Actor> getAll() throws Exception;
//
//
//    /**
//     * Deletes actor from storage.
//     * @param entity that will be deleted
//     * @throws Exception
//     */
//    public void delete(Actor entity) throws Exception;
//
//
//    /**
//     * If Actors does not exists in storage yet it will be created, otherwise it will be updated.
//     * @param entity that will be stored in storage.
//     * @throws Exception
//     */
//    public void save(Actor entity) throws Exception;
//
//
//    /**
//     * Get films count in which actor stared
//     * @param entity of Actor.
//     * @throws Exception
//     */
//    public Integer getFilmsCount(Actor entity) throws Exception;
}