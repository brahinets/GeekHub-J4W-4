package com.ysb.model.dao;

import com.ysb.model.entity.Gender;
import com.ysb.model.entity.User;
import java.util.*;


public interface UserDAO {

    /**
     * Retrieves all fields if User from storage by its identifier.
     *
     * @param id identifier of that object.
     * @return new instance of User or null if it is not find by id.
     */
    public User getUser(Integer id);


    /**
     * Return true if count of user with such mail (unique param, used as login) > 0
     * so user can register using this mail
     *
     * @return Boolean is mail free to use it
     */
    public Boolean isMailFree(String email);


    /**
     * Retrieves few fields of User (id, name, surname, avatar, isOnline) from storage by its identifier.
     *
     * @param id identifier of that object.
     * @return new instance of User or null if not fond by id.
     */
    public User getUserShort(Integer id) throws Exception;


    /**
     * Retrieves User from storage by its LOGIN ans PASSWORD.
     *
     * @param login    id of user
     * @param password password of user
     * @return new instance of User or null if not fond  by id.
     */
    public Integer getUserID(String login, String password);


    /**
     * Adding user with id = idWho to friends of user with id = idTo
     *
     * @param idWho user id who adding
     * @param idTo  user id to adding
     */
    public void addToFriends(Integer idWho, Integer idTo);


    /**
     * Deleting user with id = idWho from friends of user with id = idFrom
     *
     * @param idWho  user id who deleting
     * @param idFrom user id from deleting
     */
    public void deleteFromFriends(Integer idWho, Integer idFrom);


    /**
     * Retrieves friends count of user with specified id = userID
     *
     * @param userID user whose friends count returned
     */
    public Integer getFriendsCount(Integer userID);


    /**
     * Retrieves list of all registered people
     */
    public Collection<User> getAllPeople();


    /**
     * Retrieves list of users who are friends with user with id=userID
     *
     * @param userID user for who retrieves data
     */
    public Collection<User> getFriends(Integer userID);


    /**
     * Retrieves list of users, who subscribed on user with id=userID
     *
     * @param userID user for who retrieves data
     */
    public Collection<User> getSubscribers(Integer userID);


    /**
     * Retrieves list of users, on who user with id=userID is subscribed
     *
     * @param userID user on who each uer of list subscribed
     */
    public Collection<User> getSubscribed(Integer userID);


    /**
     * Add one user as subscriber to another
     *
     * @param idWho user who adding
     * @param idTo  user for who adding
     */
    public void addToSubscriber(Integer idWho, Integer idTo);


    /**
     * Delete one user from subscribers of another
     *
     * @param idWho  user who deleting
     * @param idFrom user from deleting
     */
    public void deleteSubscriber(Integer idWho, Integer idFrom);


    /**
     * Add one user as subscriber to another, same as addToSubscriber(), but it uses to make simpler relation
     * between users
     * EX: if user1 subscribed to user2, that mean that user2 subscriber to user1
     *
     * @param idWho user who adding
     * @param idTo  user for who adding
     */
    public void addToSubscribed(Integer idWho, Integer idTo);


    /**
     * Delete one user from subscribers of another, same as deleteSubscriber(), but it uses to make simpler relation
     * between users
     * EX: if user1 subscribed to user2, that mean that user2 subscriber to user1
     *
     * @param idWho  user who deleting
     * @param idFrom user from deleting
     */
    public void deleteSubscribed(Integer idWho, Integer idFrom);


    /**
     * Get relations between two users, return who is user with id=idWho, to user with id=idTo
     *
     * @param idWho user who related to idTo
     * @param idTo  user to which related idWho
     */
    public String getRelationBetweenUsers(Integer idWho, Integer idTo);


    /**
     * Set user status in database
     *
     * OFFLINE - false
     * ONLINE  - true
     *
     * @param userID user identifier
     * @param status user status
     */
    public void changeUserStatus(Integer userID, Boolean status);


    /**
     * Get list of users for who user with id=userID can write
     * Uses at writing new message for choosing recipients
     *
     * @param userID user who want to write to other possible people
     */
    public List<User> getUsersForWhoUserCanWrite(Integer userID);


    /**
     * Check is user with id=idWho can post on dashboard of user with id=idIn
     *
     * @param idWho user who want to post on dashboard of idIn
     * @param idIn  user in whose dashboard user idWho wants to post
     */
    public boolean canPost(Integer idWho, Integer idIn);


    /**
     * Check is user with id=idWho can write message to user with id=idIn
     * user can write, when he is not in black list, and has special (configured by other user) relation
     *
     * @param idWho user who want to write user with id = idIn
     * @param idIn  user with id=idWho wants to write to this user
     */
    public boolean canWrite(Integer idWho, Integer idIn);


    /**
     * Check is user with id=idWho is in POST black list of user with id=idIn
     *
     * @param idWho user who want to post on dashboard user with id = idIn
     * @param idIn  user with id=idWho wants to post this user
     */
    public boolean isInPostBlackList(Integer idWho, Integer idIn);


    /**
     * Add user with id=idWho to POST black list of user with id=idIn, so user idWho can't post at idIn user's dashboard
     *
     * @param idWho user who want to post on dashboard user with id = idIn
     * @param idIn  user with id=idWho wants to post this user
     */
    public void addToPostBlackList(Integer idWho, Integer idIn) ;


    /**
     * Delete user with id=idWho from POST black list of user with id=idFrom
     *
     * @param idWho user who want to post on dashboard user with id = idIn
     * @param idFrom  user with id=idWho wants to post this user
     */
    public void deleteFromPostBlackList(Integer idWho, Integer idFrom) ;


    /**
     * Check is user with id=idWho is in WRITE black list of user with id=idIn
     *
     * @param idWho user who want to write message to user with id = idIn
     * @param idIn  user with id=idWho wants to write to this user
     */
    public boolean isInWriteBlackList(Integer idWho, Integer idIn);


    /**
     * Add user with id=idWho to WRITE black list of user with id=idIn, so user idWho can't write to idIn user's dashboard
     *
     * @param idWho user who want to post on dashboard user with id = idIn
     * @param idIn  user with id=idWho wants to post this user
     */
    public void addToWriteBlackList(Integer idWho, Integer idIn) ;


    /**
     * Delete user with id=idWho from WRITE black list of user with id=idFrom
     *
     * @param idWho user who want to post on dashboard user with id = idIn
     * @param idFrom  user with id=idWho wants to post this user
     */
    public void deleteFromWriteBlackList(Integer idWho, Integer idFrom) ;


    /**
     * Retturn list of all possible genders, retrieves from DB
     */
    public Collection<Gender> getGenderList();


    /**
     * Set user as not Active
     *
     * @param id user who deleting
     */
    public void deleteUser(Integer id);


    /**
     * Get user gender object
     *
     * @param userID user whose gender is getting
     *//*
    public Gender getGender(Integer userID)*/


    /**
     * Find user matches some criteria
     *
     * @param criterias Map<criteriaName, criteriaValue>
     */
    /*public Collection<User> searchAdvanced(Map<String, Object> criterias);*/


    /**
     * Simple searchSimple with name|surname match
     *
     * @param name user name to be founded
     * @param surname user surname to be founded
     */
    public Collection<User> searchSimple(String name, String surname);
}