package com.ysb.model.service;

import com.ysb.model.dao.MailDAO;
import com.ysb.model.dao.UserDAOimpl;
import com.ysb.model.entity.Gender;
import com.ysb.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

    @Autowired
    private UserDAOimpl userDAOimpl;

    @Autowired
    private PostService postService;


    public User getOtherUser(Integer userID, Integer myID) {
        User user = getUser(userID);
        if (user != null) {
            user.setCanWrite(canWrite(myID, userID));
            user.setCanPost(canPost(myID, userID));
            user.setRelation(getRelationBetweenUsers(userID, myID));
            user.setIsInMyBlackListPost(isInPostBlackList(userID, myID));
            user.setIsInMyBlackListWrite(isInWriteBlackList(userID, myID));
            return user;
        } else {
            return null;
        }
    }


    public User getUser(Integer id) {
        User user = userDAOimpl.getUser(id);

        if (user != null) {
            user.setCountFriends(userDAOimpl.getFriendsCount(user.getId()));
            user.setListFriends(getFriends(id));
            user.setListOfWhoSubscribedOnMe(userDAOimpl.getSubscribers(id));
            user.setListOnWhoIsubscribed(userDAOimpl.getSubscribed(id));
            user.setPosts(postService.getInputPosts(user.getId()));
        }

        return user;
    }


    public User getUser(String login, String password) {
        Integer userID = userDAOimpl.getUserID(login, password);
        if (userID != null) {
            return getUser(userID);
        } else {
            return null;
        }
    }


    public User getUserShort(Integer id) {
        return userDAOimpl.getUserShort(id);
    }


    public void deleteFriendsByIDs(Integer idWho, Integer idFrom) {
        userDAOimpl.deleteFromFriends(idWho, idFrom);
        userDAOimpl.deleteFromFriends(idFrom, idWho);
        userDAOimpl.addToSubscriber(idFrom, idWho);
        userDAOimpl.addToSubscribed(idWho, idFrom);
    }


    public void submitFriendsByIDs(Integer idWho, Integer idTo) {
        userDAOimpl.addToFriends(idTo, idWho);
        userDAOimpl.addToFriends(idWho, idTo);
        userDAOimpl.deleteSubscriber(idTo, idWho);
        userDAOimpl.deleteSubscribed(idWho, idTo);
    }


    public void addSubscribersByIDs(Integer idWho, Integer idTo) {
        userDAOimpl.addToSubscriber(idWho, idTo);
        userDAOimpl.addToSubscribed(idTo, idWho);
    }


    public void deleteSubscribersByIDs(Integer idWho, Integer idFrom) {
        userDAOimpl.deleteSubscriber(idWho, idFrom);
        userDAOimpl.deleteSubscribed(idFrom, idWho);
    }


    public boolean canWrite(Integer idWho, Integer idTo) {
        return userDAOimpl.canWrite(idWho, idTo);
    }


    public boolean canPost(Integer idWho, Integer idTo) {
        return userDAOimpl.canPost(idWho, idTo);
    }


    public boolean isInPostBlackList(Integer idWho, Integer idTo) {
        return userDAOimpl.isInPostBlackList(idWho, idTo);
    }


    public void addToPostBlackList(Integer idWho, Integer idTo) {
        userDAOimpl.addToPostBlackList(idWho, idTo);
    }


    public void deleteFromPostBlackList(Integer idWho, Integer idTo) {
        userDAOimpl.deleteFromPostBlackList(idWho, idTo);
    }


    public boolean isInWriteBlackList(Integer idWho, Integer idTo) {
        return userDAOimpl.isInWriteBlackList(idWho, idTo);
    }


    public void addToWriteBlackList(Integer idWho, Integer idTo) {
        userDAOimpl.addToWriteBlackList(idWho, idTo);
    }

    public void deleteFromWriteBlackList(Integer idWho, Integer idTo) {
        userDAOimpl.deleteFromWriteBlackList(idWho, idTo);
    }


    public String getRelationBetweenUsers(Integer idWho, Integer idTo) {
        return userDAOimpl.getRelationBetweenUsers(idWho, idTo);
    }


    public Collection<User> getPeople(Integer userID) {
        Collection<User> users = userDAOimpl.getAllPeople();

        for (User user : users) {
            user.setCanWrite(userDAOimpl.canWrite(userID, user.getId()));
            user.setRelation(userDAOimpl.getRelationBetweenUsers(userID, user.getId()));
        }

        return users;
    }


    public Collection<User> getFriends(Integer userID) {
        Collection<User> users = userDAOimpl.getFriends(userID);

        for (User user : users) {
            user.setCanWrite(userDAOimpl.canWrite(userID, user.getId()));
            user.setRelation(userDAOimpl.getRelationBetweenUsers(userID, user.getId()));
        }

        return users;
    }


    public Collection<User> getForWhoUserCanWrite(Integer userID) {
        return userDAOimpl.getUsersForWhoUserCanWrite(userID);
    }


    public Collection<User> getWhoSubscribedOnMe(Integer userID) {
        Collection<User> users = userDAOimpl.getSubscribers(userID);

        for (User user : users) {
            user.setCanWrite(userDAOimpl.canWrite(userID, user.getId()));
            user.setRelation(userDAOimpl.getRelationBetweenUsers(userID, user.getId()));
        }

        return users;
    }


    public Collection<User> getOnWhoUserSubscribed(Integer userID) {
        Collection<User> users = userDAOimpl.getSubscribed(userID);

        for (User user : users) {
            user.setCanWrite(userDAOimpl.canWrite(userID, user.getId()));
            user.setRelation(userDAOimpl.getRelationBetweenUsers(userID, user.getId()));
        }

        return users;
    }


    public void saveUser(String name, String surname, String email, String password) throws Exception {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);
        userDAOimpl.save(user);
    }


    public void saveUser(User user) throws Exception {
        userDAOimpl.save(user);
    }


    public Collection<Gender> getGenderList() {
        return userDAOimpl.getGenderList();
    }


    public Boolean isMailFree(String mail) {
        return userDAOimpl.isMailFree(mail);
    }


    public Collection<User> searchSimple(Integer logedUserID, String nameOrSurname) {
        String names[] = nameOrSurname.split(" ");
        Collection<User> users;

        if (names.length == 1) {
            users = userDAOimpl.searchSimple(names[0], names[0]);
        } else {
            users = userDAOimpl.searchSimple(names[0], names[1]);
        }

        for (User user : users) {
            user.setCanWrite(userDAOimpl.canWrite(logedUserID, user.getId()));
            user.setRelation(userDAOimpl.getRelationBetweenUsers(logedUserID, user.getId()));
        }

        return users;
    }

    public void unregister(Integer id) {
        userDAOimpl.deleteUser(id);
    }
}