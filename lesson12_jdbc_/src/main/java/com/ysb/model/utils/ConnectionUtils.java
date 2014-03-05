package com.ysb.model.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {

    /* return connection to database
     *      dName    = 'geekhub'
     *      userName = 'root'
     *      userPass = ''
     * */
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/geekhub", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
}