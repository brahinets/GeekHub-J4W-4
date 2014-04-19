package com.ysb.model.dao;

import com.ysb.model.entity.User;
import com.ysb.model.utils.ConnectionUtils;
import com.ysb.model.utils.EntityUtils;
import com.ysb.model.utils.Utils;

import java.sql.*;
import java.util.*;

/**
 * Implementation of ActorDAO that uses database as a storage for objects.
 * It uses reflection to access objects fields and retrieve data to map to database tables.
 * As an identifier it uses field id of Entity class.
 */
public class UserDAOimpl implements UserDAO {
    MessageDAOimpl messageDAOimpl = new MessageDAOimpl();


    /**
     *  get user object from database storage by userID (if exists).
     *  retreives ALL User data, and manually set ignored fields
     */
    public User getUser(Integer id) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        ResultSet rs;
        User user = null;
        List<User> queryResult;
        String sqlGetUserByID = "SELECT * FROM user WHERE user.id = ?";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(sqlGetUserByID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            queryResult = EntityUtils.extractResult(User.class, rs);
            rs.close();

            if (queryResult != null) {
                user = queryResult.get(0);
                user.setCountFriends(getFriendsCount(user.getId()));
                user.setUnreadInputMail(messageDAOimpl.getUnreadMailCount(user.getId()));
                user.setListFriends(getFriends(id));
                user.setListOfWhoSubscribedOnMe(getSubscribers(id));
                user.setListOnWhoIsubscribed(getSubscribed(id));
            }
        } catch (Exception e) {
            Utils.exeption("getUser", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return user;
    }


    /** check is user with specified id exists,
     * if exists return user.id */
    public User getUserByIDShort(Integer id) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        User user = null;
        ResultSet rs;
        String sqlGetUserByID = "SELECT user.id, user.name, user.surname, user.currentAvatar, user.isOnline FROM user WHERE user.id = ? LIMIT 1";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(sqlGetUserByID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getBytes("currentAvatar"), rs.getBoolean("isOnline"));
            }
            rs.close();
        } catch (SQLException e) {
            Utils.exeption("getUserID", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return user;
    }


    /** check is user with specified login and password exists,
     * if exists return user.id */
    public Integer getUserID(String login, String password) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sqlGetUserByID = "SELECT user.id FROM user WHERE user.name = ? && user.password = ? LIMIT 1";
        Integer userID = null;

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(sqlGetUserByID);
            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                userID = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            Utils.exeption("getUserID (by login and pass)", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return userID;
    }

    /**
     * save user to database. If user new (just registered) then insert new user,
     * else, if user exists (changed some settings), then update */
    public void save(User user) throws Exception {
        Connection dbConnection = null;
        Map<String, Object> data = EntityUtils.prepareEntity(user);
        StringBuilder sql = new StringBuilder("");
        StringBuilder keys = new StringBuilder("");
        StringBuilder values = new StringBuilder("");

        try {
            dbConnection = ConnectionUtils.getConnection();

            if (user.isNew()) {
                sql.append("INSERT INTO USER ( ");

                for (String key : data.keySet()) {
                    keys.append(key).append(",");
                    if(data.get(key) instanceof Boolean || data.get(key) == null){
                        values.append(data.get(key)).append(",");
                    } else {
                        values.append("'").append(data.get(key)).append("'").append(",");
                    }
                }
                sql.append(keys.deleteCharAt(keys.length() - 1)).append(") VALUES (").append(values.deleteCharAt(values.length() - 1)).append(")");

                try (Statement statement = dbConnection.createStatement()) {
                    statement.execute(sql.toString());
                }
            } else {
                try (Statement statement = dbConnection.createStatement()) {
                    sql.append("UPDATE ").append(user.getClass().getSimpleName()).append(" SET ");
                    for (String key : data.keySet()) {
                        if(data.get(key) != null){
                            if(data.get(key) instanceof Boolean){
                                sql.append(key).append(" = ").append(data.get(key)).append(" ,");
                            } else {
                                sql.append(key).append(" = '").append(data.get(key)).append("' ,");
                            }
                        }
                    }
                    sql.deleteCharAt(sql.length() - 1).append(" WHERE id = '").append(user.getId()).append("'");
                    statement.execute(sql.toString());
                }
            }
        } catch (SQLException e) {
            Utils.exeption("userSave", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
        }
    }


    /** add user=idWho to friends of user=idTo */
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
            Utils.exeption("addToFriends", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }
    }


    /** delete user=idWho from friends of user=idFrom */
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
            Utils.exeption("deleteFromFriends", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }
    }


    /** return count of user with specified userID friends  */
    public Integer getFriendsCount(Integer userID) {

        System.out.println(" uid= " + userID);

        Connection dbConnection = null;
        PreparedStatement ps = null;
        String sql = "SELECT COUNT(*) FROM user_user_relation WHERE user_user_relation.idWho = ? AND user_user_relation.idRelationType = 1";

        /*"SELECT COUNT(*) FROM user\n" +
         "JOIN user_user_relation ON user.id=user_user_relation.idTo\n" +
         "JOIN relation_types ON relation_types.id  = user_user_relation.idRelationType\n" +
         "WHERE user_user_relation.idWho = ?  AND relation_types.name = \"friend\"";*/
        Integer count = 0;

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(sql);
            ps.setInt(1, userID);

            System.out.println(ps.toString());

            ResultSet rs = ps.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            Utils.exeption("getFriendsCount", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return count;
    }


    /** get list of all people */
    public Collection<User> getAllPeople() {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        Set<User> people = new HashSet<>();
        String getPeopleSQL = "SELECT user.id, user.name, user.surname, user.currentAvatar, user.isOnline FROM user";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(getPeopleSQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                people.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getBytes("currentAvatar"), rs.getBoolean("isOnline")));
            }
            rs.close();
        } catch (SQLException e) {
            Utils.exeption("getPeople", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return people;
    }


    /** return list of users who are friends with user with id=userID */
    public Collection<User> getFriends(Integer userID) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        Set<User> friends = new HashSet<>();
        String getFriendsSQL ="SELECT user.id, user.name, user.surname, user.currentAvatar, user.isOnline FROM user \n" +
                              "JOIN user_user_relation ON user.id=user_user_relation.idTo \n" +
                              "JOIN relation_types ON relation_types.id  = user_user_relation.idRelationType\n" +
                              "WHERE user_user_relation.idWho = ?  AND relation_types.name = \"friend\"";
        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(getFriendsSQL);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                friends.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getBytes("currentAvatar"), rs.getBoolean("isOnline")));
            }
            rs.close();
        } catch (SQLException e) {
            Utils.exeption("getFriends", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return friends;
    }


    /** return list of users, who subscribed on user with id=userID */
    public Collection<User> getSubscribers(Integer userID){
        Connection dbConnection = null;
        PreparedStatement ps = null;
        Set<User> subscribers = new HashSet<>();
        String getSubscribersSQL =
                "SELECT user.id, user.name, user.surname, user.currentAvatar, user.isOnline FROM user \n" +
                        "JOIN user_user_relation ON user.id=user_user_relation.idWho \n" +
                        "JOIN relation_types ON relation_types.id  = user_user_relation.idRelationType\n" +
                        "WHERE user_user_relation.idTo = ?  AND relation_types.name = \"subscriber\"";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(getSubscribersSQL);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subscribers.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getBytes("currentAvatar"), rs.getBoolean("isOnline")));
            }
        } catch (SQLException e) {
            Utils.exeption("getSubscribers", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return subscribers;
    }


    /** return list of users, on who user with id=userID is subscribed */
    public Collection<User> getSubscribed(Integer userID){
        Connection dbConnection = null;
        PreparedStatement ps = null;
        Set<User> subscribed = new HashSet<>();
        String getSubscribedSQL =
                "SELECT user.id, user.name, user.surname, user.currentAvatar, user.isOnline  FROM user \n" +
                        "JOIN user_user_relation ON user.id=user_user_relation.idTo \n" +
                        "JOIN relation_types ON relation_types.id  = user_user_relation.idRelationType\n" +
                        "WHERE user_user_relation.idWho = ?  AND relation_types.name = \"subscriber\"";
        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(getSubscribedSQL);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subscribed.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getBytes("currentAvatar"), rs.getBoolean("isOnline")));
            }
        } catch (SQLException e) {
            Utils.exeption("getSubscribed", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return subscribed;
    }











    /* add to subscribers,*/
    public void addToSubscriber(Integer idWho, Integer idTo) throws SQLException {
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
            Utils.exeption("addToSubscriber", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }
    }


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
            Utils.exeption("deleteSubscriber", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }
    }


    /**  subscribe user=idWho to user=idTo, so now idWho is subscribed on idTo */
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
            Utils.exeption("addToSubscribed", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }
    }


    /**  unsubscribe user=idWho from user=idFrom, so now idWho is not subscribed on idFrom */
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
            Utils.exeption("addToSubscribed", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }
    }




    public String getRelationBetweenUsers(Integer idWho, Integer idTo) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        String sqlGetRelationBetweenUsers = "SELECT relation_types.name FROM user_user_relation " +
                "JOIN relation_types ON user_user_relation.idRelationType = relation_types.id " +
                "WHERE user_user_relation.idWho = ? AND user_user_relation.idTo = ?";
        String relation = "";

        try {
            dbConnection = ConnectionUtils.getConnection();
            dbConnection.setAutoCommit(false);
            ps = dbConnection.prepareStatement(sqlGetRelationBetweenUsers);
            ps.setInt(1, idWho);
            ps.setInt(2, idTo);
            ResultSet rs = ps.executeQuery();
            dbConnection.commit();
            if (rs.next()) {
                relation = rs.getString(1);
            }
            rs.close();
        } catch (SQLException e) {
            dbConnection.rollback();
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return relation;
    }


    public static void loadAvatar(Integer userID, byte[] image) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement ps = null;

        try {
            String sqlWhoCanWrite = "UPDATE user SET user.currentAvatar = ? WHERE  user.id = ?";
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(sqlWhoCanWrite);
            ps.setBytes(1, image);
            ps.setInt(2, userID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("exc in loadAvatar dao + " + e.getMessage());
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }
    }


    /** set user flag in database, OFFLINE when status = false, or ONLINE - true */
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
            Utils.exeption("changeUserStatus", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }
    }


    /** get list of users for who user with id=userID can write.
     * Uses at writing new message for choosing recipients */
    public List<User> getUsersForWhoUserCanWrite(Integer userID) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        List<User> whoUserCanWriteList = new ArrayList<>();

        String sqlWhoUserCanWrite = "SELECT user.id, user.name, user.surname, user.currentAvatar, user.isOnline FROM user\n" +
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
            Utils.exeption("getUsersForWhoUserCanWrite",e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return whoUserCanWriteList;
    }


    /** check is user with id=idWho can post on dashboard of user with id=idIn */
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
            Utils.exeption("canPost", e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return canPost;
    }


    /** check is user with id=idWho can write message to user with id=idIn */
    public boolean canWrite(Integer idWho, Integer idIn) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        String canUserWriteSQL= "SELECT count(*)  FROM blacklist_write WHERE blacklist_write.idIn = ? AND blacklist_write.idWho = ?";
        boolean canWrite = false;

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(canUserWriteSQL);
            ps.setInt(1, idIn);
            ps.setInt(2, idWho);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                canWrite = (rs.getInt(1)==0 && !idWho.equals(idIn));
            }
            rs.close();
        } catch (SQLException e) {
            Utils.exeption("canWrite",e);
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return canWrite;
    }

}
