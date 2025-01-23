package org.example.Repository.Common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.DatabaseConnectionFailed;

import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/escaperoomdb";
    private static final String USER = "root";
    private String PASSWORD;
    private static final int MAX_CONNECTION_OPTIONS = 4;
    private String workingPassword = null;

    Logger logger = LogManager.getLogger(DatabaseConnection.class);

    void setConnectionOption(int connectionOption) {
        switch (connectionOption) {
            case 0://Albert
                PASSWORD = "Chefwork135731";
                break;
            case 1://Marc
                PASSWORD = "mbernar910";
                break;
            case 2://Inga
                PASSWORD = "password";
                break;
            case 3://Pau
                PASSWORD = "";
                break;
            default:
                throw new DatabaseConnectionFailed("No connection options available.");
        }
    }

    public Connection dbConnect() {
        Connection connection = null;
        if (workingPassword != null) {
            try {
                connection = DriverManager.getConnection(URL, USER, workingPassword);
                logger.info("Connection established successfully");
                return connection;
            } catch (SQLException e) {
                logger.error("Connection failed, trying another password.");
                workingPassword = null;
            }
        }
        for (int i = 0; i < MAX_CONNECTION_OPTIONS; i++) {
            setConnectionOption(i);
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                logger.info("Connection established successfully");
                workingPassword = PASSWORD;
                i = MAX_CONNECTION_OPTIONS;
            } catch (SQLException e) {
                logger.error("Connection failed, trying with another connection.");
            }
        }
        if (connection == null) {
            throw new DatabaseConnectionFailed("All connection attempts failed.");
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
            logger.info("Connection closed.");
        } catch (SQLException e) {
            logger.error("Failed to close connection: ", e);
        }
    }
}


