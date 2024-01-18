package com.laptrinhweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {

    private static String DB_URL = "jdbc:mysql://localhost:3306/javasql42022";
    private static String USER = "root";
    private static String PASS = "2705";

    public static Connection getConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            return  connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
