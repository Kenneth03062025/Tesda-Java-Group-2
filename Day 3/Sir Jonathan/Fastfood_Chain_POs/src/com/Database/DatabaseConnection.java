package com.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DatabaseConnection {

    protected final static String URL = "jdbc:mysql://localhost:3306/db_fastfood_chain_pos";
    protected final static String USERNAME = "root";
    protected final static String PASSWORD = "";
    protected final static String DRIVER = "com.mysql.jdbc.Driver";

    protected static Connection connection;
    protected static Statement statement;
    protected static ResultSet resultSet;
    protected static PreparedStatement preparedStatement;

    protected void dbConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
       //     System.out.println("Successfully Connected");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection Failed" + e.getMessage());
        }
    }

    protected static void disconnectDB() throws SQLException {
        try {

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            connection.close();
        }
    }
    protected static void checkingDatabase(){
        try {
            if(preparedStatement != null) preparedStatement.close();
            if(connection != null) connection.close();
        } catch (Exception e) {
            System.out.println("Error disconnecting from DB: " + e.getMessage());
        }
    }
}
