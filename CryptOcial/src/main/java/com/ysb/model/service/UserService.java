package com.ysb.model.service;

import com.ysb.model.dao.UserDAOimpl;
import com.ysb.model.entity.User;

import java.sql.SQLException;
import java.util.Collection;

public class UserService {
    private static UserDAOimpl userDAOimpl = new UserDAOimpl();


    public static User getUser(Integer id) throws Exception {
        return userDAOimpl.getUser(id);
    }


    public static User getUser(String login, String password) throws Exception {
        Integer userID = userDAOimpl.getUserID(login, password);
        if(userID != null){
            return userDAOimpl.getUser(userID);
        } else {
            return null;
        }
    }


    public static User getUserShort(Integer id) throws Exception {
        return userDAOimpl.getUserByIDShort(id);
    }


    public static void deleteFriendsByIDs(Integer idWho, Integer idFrom) throws SQLException {
        userDAOimpl.deleteFromFriends(idWho, idFrom);
        userDAOimpl.deleteFromFriends(idFrom, idWho);
        userDAOimpl.addToSubscriber(idFrom, idWho);
        userDAOimpl.addToSubscribed(idWho, idFrom);
    }


    public static void submitFriendsByIDs(Integer idWho, Integer idTo) throws SQLException {
        userDAOimpl.addToFriends(idTo, idWho);
        userDAOimpl.addToFriends(idWho, idTo);
        userDAOimpl.deleteSubscriber(idTo, idWho);
        userDAOimpl.deleteSubscribed(idWho, idTo);
    }


    public static void addSubscribersByIDs(Integer idWho, Integer idTo) throws SQLException {
        userDAOimpl.addToSubscriber(idWho, idTo);
        userDAOimpl.addToSubscribed(idTo, idWho);
    }


    public static void deleteSubscribersByIDs(Integer idWho, Integer idFrom) throws SQLException {
        userDAOimpl.deleteSubscriber(idWho, idFrom);
        userDAOimpl.deleteSubscribed(idFrom, idWho);
    }


    public static boolean canWrite(Integer idWho, Integer idTo) throws SQLException {
        return userDAOimpl.canWrite(idWho, idTo);
    }


    public static boolean canPost(Integer idWho, Integer idTo) throws SQLException {
        return userDAOimpl.canPost(idWho, idTo);
    }


    public static String getRelationBetweenUsers(Integer idWho, Integer idTo) throws SQLException {
        return userDAOimpl.getRelationBetweenUsers(idWho, idTo);
    }


    public static Collection<User> getPeople(Integer userID) throws Exception {
        Collection<User> users = userDAOimpl.getAllPeople();

        for (User user : users) {
            user.setCanWrite(userDAOimpl.canWrite(userID, user.getId()));
            user.setRelation(userDAOimpl.getRelationBetweenUsers(userID, user.getId()));
        }

        return users;
    }


    public static Collection<User> getFriends(Integer userID) throws Exception {
        Collection<User> users = userDAOimpl.getFriends(userID);

        for (User user : users) {
            user.setCanWrite(userDAOimpl.canWrite(userID, user.getId()));
            user.setRelation(userDAOimpl.getRelationBetweenUsers(userID, user.getId()));
        }

        return users;
    }


    public static Collection<User> getForWhoUserCanWrite(Integer userID) throws Exception {
        return userDAOimpl.getUsersForWhoUserCanWrite(userID);
    }


    public static Collection<User> getWhoSubscribedOnMe(Integer userID) throws Exception {
        Collection<User> users = userDAOimpl.getSubscribers(userID);

        for (User user : users) {
            user.setCanWrite(userDAOimpl.canWrite(userID, user.getId()));
            user.setRelation(userDAOimpl.getRelationBetweenUsers(userID, user.getId()));
        }

        return users;
    }


    public static Collection<User> getOnWhoUserSubscribed(Integer userID) throws Exception {
        Collection<User> users = userDAOimpl.getSubscribed(userID);

        for (User user : users) {
            user.setCanWrite(userDAOimpl.canWrite(userID, user.getId()));
            user.setRelation(userDAOimpl.getRelationBetweenUsers(userID, user.getId()));
        }

        return users;
    }

    public static boolean saveUser(String name, String surname, String email, String password) throws Exception {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);

        userDAOimpl.save(user);

        return true;
    }

    public static boolean saveUser(User user) throws Exception {
        userDAOimpl.save(user);
        userDAOimpl.loadAvatar(user.getId(), user.getCurrentAvatar());
        return true;
    }
}