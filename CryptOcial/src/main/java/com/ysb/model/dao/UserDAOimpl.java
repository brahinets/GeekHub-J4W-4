package com.ysb.model.dao;

import com.ysb.model.entity.Gender;
import com.ysb.model.entity.User;
import com.ysb.model.utils.ConnectionUtils;
import com.ysb.model.utils.EntityUtils;
import com.ysb.model.utils.Utils;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

/**
 * Implementation of UserDAO that uses database as a storage for objects.
 * It uses reflection to access objects fields and retrieve data to map to database tables.
 * As an identifier it uses field id of Entity class.
 */

// TODO advanced searchSimple
// TODO friends recommendation system
// TODO user can choose who can write|post him (friends, all, ...)

@Repository
public class UserDAOimpl extends EntityDAOimpl implements UserDAO {
    private String shortFields = " user.id, user.name, user.surname, user.currentAvatar, user.whereFrom, user.isOnline ";

    @Override
    public User getUser(Integer id) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        ResultSet rs;
        User user = null;
        List<User> queryResult;
        String sqlGetUserByID = "SELECT * FROM user WHERE user.id = ? AND user.isActive = ?";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(sqlGetUserByID);
            ps.setInt(1, id);
            ps.setBoolean(2, true);
            rs = ps.executeQuery();
            queryResult = EntityUtils.extractResult(User.class, rs);
            rs.close();

            if (queryResult != null) {
                user = queryResult.get(0);

            }
        } catch (Exception e) {
            Utils.exception("getUser", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return user;
    }


    @Override
    public User getUserShort(Integer id) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        User user = null;
        ResultSet rs;
        String sqlGetUserByID = "SELECT " + shortFields + " FROM user WHERE user.id = ?  AND user.isActive = ? LIMIT 1";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(sqlGetUserByID);
            ps.setInt(1, id);
            ps.setBoolean(2, true);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getBytes("currentAvatar"), rs.getBoolean("isOnline"), rs.getString("whereFrom"));
            }
            rs.close();
        } catch (SQLException e) {
            Utils.exception("getUserShort", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return user;
    }


    @Override
    public Integer getUserID(String login, String password) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sqlGetUserByID = "SELECT user.id FROM user WHERE user.email = ? AND user.password = ?  AND user.isActive = ? LIMIT 1";
        Integer userID = null;

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(sqlGetUserByID);
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setBoolean(3, true);
            rs = ps.executeQuery();
            if (rs.next()) {
                userID = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            Utils.exception("getUserID (by login and pass)", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return userID;
    }


    @Override
    public void addToFriends(Integer idWho, Integer idTo) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        String sqlAddFriends = "INSERT INTO user_user_relation (idWho, idTo, idRelationType) VALUES (?, ?, '1')";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(sqlAddFriends);
            ps.setInt(1, idWho);
            ps.setInt(2, idTo);
            ps.executeUpdate();
        } catch (SQLException e) {
            Utils.exception("addToFriends", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }
    }


    @Override
    public void deleteFromFriends(Integer idWho, Integer idFrom) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        String sqlDeleteFriend = "DELETE FROM user_user_relation WHERE (idWho=?) AND (idTo=?) AND (idRelationType='1') LIMIT 1";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(sqlDeleteFriend);
            ps.setInt(1, idWho);
            ps.setInt(2, idFrom);
            ps.executeUpdate();
        } catch (SQLException e) {
            Utils.exception("deleteFromFriends", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }
    }


    @Override
    public Integer getFriendsCount(Integer userID) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        Integer count = 0;
        String friendsCountSQL = "SELECT count(*) FROM user_user_relation WHERE (idWho = ?) AND (idRelationType = ?)";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(friendsCountSQL);
            ps.setInt(1, userID);
            ps.setBoolean(2, false);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
        } catch (Exception e) {
            Utils.exception("getFriendsCount", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return count;
    }


    @Override
    public Collection<User> getAllPeople() {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        Set<User> people = new HashSet<>();
        String getPeopleSQL = "SELECT " + shortFields + " FROM user WHERE user.isActive = ?";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(getPeopleSQL);
            ps.setBoolean(1, true);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                people.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getBytes("currentAvatar"), rs.getBoolean("isOnline"), rs.getString("whereFrom")));
            }
            rs.close();
        } catch (SQLException e) {
            Utils.exception("seeAllPeople", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return people;
    }


    @Override
    public Collection<User> getFriends(Integer userID) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        Set<User> friends = new HashSet<>();
        String getFriendsSQL = "SELECT " + shortFields + " FROM user \n" +
                "JOIN user_user_relation ON user.id=user_user_relation.idTo \n" +
                "JOIN relation_types ON relation_types.id  = user_user_relation.idRelationType\n" +
                "WHERE user_user_relation.idWho = ?  AND relation_types.name = \"friend\"  AND user.isActive = ?";
        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(getFriendsSQL);
            ps.setInt(1, userID);
            ps.setBoolean(2, true);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                friends.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getBytes("currentAvatar"), rs.getBoolean("isOnline"), rs.getString("whereFrom")));
            }
            rs.close();
        } catch (SQLException e) {
            Utils.exception("getFriends", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return friends;
    }


    @Override
    public Collection<User> getSubscribers(Integer userID) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        Set<User> subscribers = new HashSet<>();
        String getSubscribersSQL =
                "SELECT " + shortFields + "  FROM user \n" +
                        "JOIN user_user_relation ON user.id=user_user_relation.idWho \n" +
                        "JOIN relation_types ON relation_types.id  = user_user_relation.idRelationType\n" +
                        "WHERE user_user_relation.idTo = ?  AND relation_types.name = \"subscriber\"  AND user.isActive = ?";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(getSubscribersSQL);
            ps.setInt(1, userID);
            ps.setBoolean(2, true);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subscribers.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getBytes("currentAvatar"), rs.getBoolean("isOnline"), rs.getString("whereFrom")));
            }
        } catch (SQLException e) {
            Utils.exception("getSubscribers", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return subscribers;
    }


    @Override
    public Collection<User> getSubscribed(Integer userID) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        Set<User> subscribed = new HashSet<>();
        String getSubscribedSQL =
                "SELECT " + shortFields + "  FROM user \n" +
                        "JOIN user_user_relation ON user.id=user_user_relation.idTo \n" +
                        "JOIN relation_types ON relation_types.id  = user_user_relation.idRelationType\n" +
                        "WHERE user_user_relation.idWho = ?  AND relation_types.name = \"subscriber\"  AND user.isActive = ?";
        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(getSubscribedSQL);
            ps.setInt(1, userID);
            ps.setBoolean(2, true);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subscribed.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getBytes("currentAvatar"), rs.getBoolean("isOnline"), rs.getString("whereFrom")));
            }
        } catch (SQLException e) {
            Utils.exception("getSubscribed", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return subscribed;
    }


    @Override
    public void addToSubscriber(Integer idWho, Integer idTo) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        String sqlAddFriend = "INSERT INTO user_user_relation (idWho, idTo, idRelationType) VALUES (?, ?, '2')";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(sqlAddFriend);
            ps.setInt(1, idWho);
            ps.setInt(2, idTo);
            ps.executeUpdate();
        } catch (SQLException e) {
            Utils.exception("addToSubscriber", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }
    }


    @Override
    public void deleteSubscriber(Integer idWho, Integer idFrom) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        String sqlDeleteSubscriber = "DELETE FROM user_user_relation WHERE (idWho=?) AND (idTo=?) AND (idRelationType='2') LIMIT 1";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(sqlDeleteSubscriber);
            ps.setInt(1, idWho);
            ps.setInt(2, idFrom);
            ps.executeUpdate();
        } catch (SQLException e) {
            Utils.exception("deleteSubscriber", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }
    }


    @Override
    public void addToSubscribed(Integer idWho, Integer idTo) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        String sqlAddFriend = "INSERT INTO user_user_relation (idWho, idTo, idRelationType) VALUES (?, ?, '3')";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(sqlAddFriend);
            ps.setInt(1, idWho);
            ps.setInt(2, idTo);
            ps.executeUpdate();
        } catch (SQLException e) {
            Utils.exception("addToSubscribed", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }
    }


    @Override
    public void deleteSubscribed(Integer idWho, Integer idFrom) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        String sqlDeleteSubscriber = "DELETE FROM user_user_relation WHERE (idWho=?) AND (idTo=?) AND (idRelationType='3') LIMIT 1";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(sqlDeleteSubscriber);
            ps.setInt(1, idWho);
            ps.setInt(2, idFrom);
            ps.executeUpdate();
        } catch (SQLException e) {
            Utils.exception("deleteSubscribed", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }
    }


    @Override
    public String getRelationBetweenUsers(Integer idWho, Integer idTo) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        String sqlGetRelationBetweenUsers = "SELECT relation_types.name FROM user_user_relation " +
                "JOIN relation_types ON user_user_relation.idRelationType = relation_types.id " +
                "WHERE user_user_relation.idWho = ? AND user_user_relation.idTo = ?";
        String relation = "";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(sqlGetRelationBetweenUsers);
            ps.setInt(1, idWho);
            ps.setInt(2, idTo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                relation = rs.getString(1);
            }
            rs.close();
        } catch (SQLException e) {
            Utils.exception("getRelationBetweenUsers", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return relation;
    }


    /*
    @Override
    public Gender getGender(Integer userID) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        ResultSet rs;
        List<Gender> queryResult;
        String sqlGetUserByID = "SELECT gender.id, gender.name FROM gender JOIN user ON user.gender = gender.id WHERE user.id = ?";
        Gender gender = null;

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(sqlGetUserByID);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            queryResult = EntityUtils.extractResult(Gender.class, rs);

            if (queryResult != null) {
                gender = queryResult.get(0);
            }
            rs.close();
        } catch (Exception e) {
            Utils.exception("getGender", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return gender;
    }*/


    @Override
    public void changeUserStatus(Integer userID, Boolean status) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        String changeUserStatusSQL = "UPDATE user SET user.isOnline = ? WHERE user.id = ? LIMIT 1";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(changeUserStatusSQL);
            ps.setBoolean(1, status);
            ps.setInt(2, userID);
            ps.executeUpdate();
        } catch (SQLException e) {
            Utils.exception("changeUserStatus", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }
    }


    @Override
    public List<User> getUsersForWhoUserCanWrite(Integer userID) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        List<User> whoUserCanWriteList = new ArrayList<>();

        String sqlWhoUserCanWrite = "SELECT " + shortFields + " FROM user\n" +
                "WHERE user.id NOT IN (SELECT blacklist_write.idWho FROM blacklist_write) AND user.id != ?";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(sqlWhoUserCanWrite);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                whoUserCanWriteList.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getBytes("currentAvatar"), rs.getBoolean("isOnline")));
            }
        } catch (SQLException e) {
            Utils.exception("getUsersForWhoUserCanWrite", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return whoUserCanWriteList;
    }


    @Override
    public boolean canPost(Integer idWho, Integer idIn) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        boolean canPost = false;
        String canUserPostSQL = "SELECT count(*) FROM blacklist_post WHERE blacklist_post.idIn = ? AND blacklist_post.idWho = ?";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(canUserPostSQL);
            ps.setInt(1, idIn);
            ps.setInt(2, idWho);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                canPost = (rs.getInt(1) == 0 || idWho.equals(idIn));
            }
            rs.close();
        } catch (SQLException e) {
            Utils.exception("canPost", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return canPost;
    }


    @Override
    public boolean canWrite(Integer idWho, Integer idIn) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        String canUserWriteSQL = "SELECT count(*)  FROM blacklist_write WHERE blacklist_write.idIn = ? AND blacklist_write.idWho = ?";
        boolean canWrite = false;

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(canUserWriteSQL);
            ps.setInt(1, idIn);
            ps.setInt(2, idWho);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                canWrite = (rs.getInt(1) == 0 && !idWho.equals(idIn));
            }
            rs.close();
        } catch (SQLException e) {
            Utils.exception("canWrite", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return canWrite;
    }


    @Override
    public boolean isInPostBlackList(Integer idWho, Integer idIn) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        String canUserWriteSQL = "SELECT count(*)  FROM blacklist_post WHERE blacklist_post.idIn = ? AND blacklist_post.idWho = ?";
        boolean isInPostBlackList = false;

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(canUserWriteSQL);
            ps.setInt(1, idIn);
            ps.setInt(2, idWho);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isInPostBlackList = rs.getInt(1) > 0;
            }
            rs.close();
        } catch (SQLException e) {
            Utils.exception("isInPostBlackList", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return isInPostBlackList;
    }


    @Override
    public void addToPostBlackList(Integer idWho, Integer idIn) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        String canUserWriteSQL = "INSERT INTO blacklist_post(idIn, idWho) VALUES (?, ?)";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(canUserWriteSQL);
            ps.setInt(1, idIn);
            ps.setInt(2, idWho);
            ps.executeUpdate();
        } catch (SQLException e) {
            Utils.exception("addToPostBlackList", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }
    }


    @Override
    public void deleteFromPostBlackList(Integer idWho, Integer idFrom) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        String canUserWriteSQL = "DELETE FROM blacklist_post WHERE blacklist_post.idIn = ? AND blacklist_post.idWho = ?";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(canUserWriteSQL);
            ps.setInt(1, idFrom);
            ps.setInt(2, idWho);
            ps.executeUpdate();
        } catch (SQLException e) {
            Utils.exception("deleteFromPostBlackList", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }
    }


    @Override
    public boolean isInWriteBlackList(Integer idWho, Integer idIn) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        String canUserWriteSQL = "SELECT count(*)  FROM blacklist_write WHERE blacklist_write.idIn = ? AND blacklist_write.idWho = ?";
        boolean isInWriteBlackList = false;

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(canUserWriteSQL);
            ps.setInt(1, idIn);
            ps.setInt(2, idWho);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isInWriteBlackList = (rs.getInt(1) > 0);
            }
            rs.close();
        } catch (SQLException e) {
            Utils.exception("isInWriteBlackList", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return isInWriteBlackList;
    }


    @Override
    public void addToWriteBlackList(Integer idWho, Integer idIn) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        String canUserWriteSQL = "INSERT INTO blacklist_write(idIn, idWho) VALUES (?, ?)";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(canUserWriteSQL);
            ps.setInt(1, idIn);
            ps.setInt(2, idWho);
            ps.executeUpdate();
        } catch (SQLException e) {
            Utils.exception("addToWriteBlackList", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }
    }


    @Override
    public void deleteFromWriteBlackList(Integer idWho, Integer idFrom) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        String canUserWriteSQL = "DELETE FROM blacklist_write WHERE blacklist_write.idIn = ? AND blacklist_write.idWho = ?";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(canUserWriteSQL);
            ps.setInt(1, idFrom);
            ps.setInt(2, idWho);
            ps.executeUpdate();
        } catch (SQLException e) {
            Utils.exception("deleteFromWriteBlackList", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }
    }


    @Override
    public Collection<Gender> getGenderList() {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        ResultSet rs;
        List<Gender> queryResult = null;
        String sqlGetGenderList = "SELECT gender.id, gender.name FROM gender";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(sqlGetGenderList);
            rs = ps.executeQuery();
            queryResult = EntityUtils.extractResult(Gender.class, rs);
            rs.close();
        } catch (Exception e) {
            Utils.exception("getGenderList", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return queryResult;
    }


    @Override
    public Collection<User> searchSimple(String name, String surname) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        List<User> people = new ArrayList<>();
        String searcnNameSurname = "SELECT " + shortFields + " FROM user WHERE (name LIKE '%" + name + "%' OR user.surname  LIKE '%" + surname + "%') OR (surname LIKE '%" + name + "%' OR name  LIKE '%" + surname + "%')";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(searcnNameSurname);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                people.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getBytes("currentAvatar"), rs.getBoolean("isOnline")));
            }
        } catch (SQLException e) {
            Utils.exception("searchSimple", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return people;
    }


    @Override
    public void deleteUser(Integer id) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        String canUserWriteSQL = "UPDATE user SET user.isActive = ? WHERE user.id = ? LIMIT 1";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(canUserWriteSQL);
            ps.setBoolean(1, false);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            Utils.exception("deleteUser", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }
    }


    @Override
    public Boolean isMailFree(String email) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sqlGetUserByID = "SELECT COUNT(*) FROM user WHERE user.email = ?";
        Boolean free = false;

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(sqlGetUserByID);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                free = rs.getInt(1) == 0;
            }
            rs.close();
        } catch (SQLException e) {
            Utils.exception("isMailFree", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return free;
    }


}
