package org.example.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.DatabaseConnectionFailed;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection implements RepositoryMGMT {
    private final static String URL = "jdbc:mysql://127.0.0.2:3306/mydb";
    private final static String USER = "root";
    private final static  String PASSWORD = "mbernar910";

    Logger logger = LogManager.getLogger(DatabaseConnection.class);

    public Connection dbConnect() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.info("Connection established successfully.");
        } catch (SQLException e) {
            logger.error("Connection failed.", e);
            throw new DatabaseConnectionFailed(e.getMessage());
        }
        return connection;
    }
}
