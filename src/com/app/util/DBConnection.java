package com.app.util;

import java.sql.*;

public class DBConnection {

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private final static String URL = "jdbc:mysql://localhost:3306/db_ffchain_pos";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "";

    public Connection connection;

    public DBConnection() {
        connect();
    }

    public void connect() {
        try {
           // Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           // System.out.println("Connected Successfully");
        } catch (Exception e) {
            System.out.println("Something Went Wrong " + e);
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void backOff() {
        try {
            System.out.println("Rolling Back...");
            connection.rollback();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
