package com.ysb.model.dao;
//

import com.ysb.model.entity.Message;


import com.ysb.model.utils.ConnectionUtils;
import com.ysb.model.utils.EntityUtils;

import java.sql.*;
import java.util.*;


/**
* Implementation of ActorDAO that uses database as a storage for objects.
* It uses reflection to access objects fields and retrieve data to map to database tables.
* As an identifier it uses field id of Entity class.
*/
public class MessageDAOimpl {

    /*@Autowired
    private DataSource dataSource;
*/
    public Message getMessageByID(Integer id)  throws Exception{
        /*System.out.println("in get m by id");
        String sql = "SELECT * FROM message WHERE id = " + id;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query(sql, new RowMapper<Message>() {
            @Override
            public Message mapRow(ResultSet resultSet, int i) throws SQLException {
                return new MessageExtractor().extractData(resultSet);
            }
        }).get(0);*/
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String sqlGetMessageByID = "SELECT * FROM message WHERE id = ?";

        try {
            dbConnection = ConnectionUtils.getConnection();
            dbConnection.setAutoCommit(false);
            preparedStatement = dbConnection.prepareStatement(sqlGetMessageByID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            dbConnection.commit();

            List<Message> queryResult = EntityUtils.extractResult(Message.class, rs);

            if(!queryResult.isEmpty()){
                return queryResult.get(0);
            }
        } catch (SQLException e) {
            dbConnection.rollback();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }

        return null;

    }

public List<Message> getInputMessagesByUserID(Integer id)  throws Exception{

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String sqlGetMessagesByUserID = "SELECT * FROM message WHERE recipient = ?";

        System.out.println("in input message");

        try {
            dbConnection = ConnectionUtils.getConnection();
            dbConnection.setAutoCommit(false);
            preparedStatement = dbConnection.prepareStatement(sqlGetMessagesByUserID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            dbConnection.commit();
            List<Message> queryResult = EntityUtils.extractResult(Message.class, rs);

            if(!queryResult.isEmpty()){
                return queryResult;
            }
        } catch (SQLException e) {
            dbConnection.rollback();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }

        return new ArrayList<>();

    }

public List<Message> getOutputMessagesByUserID(Integer id)  throws Exception{
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String sqlGetMessagesByUserID = "SELECT * FROM message WHERE sender = ?";

        try {
            dbConnection = ConnectionUtils.getConnection();
            dbConnection.setAutoCommit(false);
            preparedStatement = dbConnection.prepareStatement(sqlGetMessagesByUserID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            dbConnection.commit();

            List<Message> queryResult = EntityUtils.extractResult(Message.class, rs);

            if(!queryResult.isEmpty()){
                return queryResult;
            }
        } catch (SQLException e) {
            dbConnection.rollback();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }

        return new ArrayList<>();
    }


public boolean sendMessage(Message message) throws Exception {
        Connection dbConnection = null;
        Map<String, Object> data = EntityUtils.prepareEntity(message);
        StringBuilder sql = new StringBuilder("");
        StringBuilder keys = new StringBuilder("");
        StringBuilder values = new StringBuilder("");
        Statement statement;

        try {
            dbConnection = ConnectionUtils.getConnection();
            dbConnection.setAutoCommit(false);
            statement = dbConnection.createStatement();
            sql.append("INSERT INTO MESSAGE ( ");
            for(String key:data.keySet()) {
                keys.append(key).append(",");
                values.append("'").append(data.get(key)).append("'").append(",");
            }
            sql.append(keys.deleteCharAt(keys.length() - 1) + ") VALUES (" + values.deleteCharAt(values.length() - 1) + ")");
            System.out.println(sql);
            statement.execute(sql.toString());
            dbConnection.commit();

            return true;
        } catch (SQLException e) {
            dbConnection.rollback();
            System.out.println(e.getErrorCode());
            return false;
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
        }
    }

























    /** get count of unread messages of user with specified userID */
    public Integer getUnreadMailCount(Integer userID) {
        Connection dbConnection = null;
        PreparedStatement ps = null;
        Integer count = 0;
        String countOfUnreadMailSQL = "SELECT count(*) FROM message WHERE recipient = ? AND isRead = ?";

        try {
            dbConnection = ConnectionUtils.getConnection();
            ps = dbConnection.prepareStatement(countOfUnreadMailSQL);
            ps.setInt(1, userID);
            ps.setBoolean(2, false);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Exc in getUnreadMailCount : " + e.getMessage());
        } finally {
            ConnectionUtils.closeConnection(dbConnection);
            ConnectionUtils.closePreparedStatement(ps);
        }

        return count;
    }
}