package org.example.Repository.Common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.DatabaseConnectionFailed;

import java.sql.*;

public class DatabaseConnection {

    private String URL;
    private String USER = "root";
    private String PASSWORD;
    private static final int MAX_CONNECTION_OPTIONS = 4;
    private static int connectionSetting = -1;

    Logger logger = LogManager.getLogger(DatabaseConnection.class);

    void setConnectionOption(int connectionOption) {
        switch (connectionOption) {
            case 0://Albert
                URL = "jdbc:mysql://localhost:3306/escaperoomdb";
                PASSWORD = "Chefwork135731";
                break;
            case 1://Marc
                URL = "jdbc:mysql://127.0.0.2:3306/escaperoomdb";
                PASSWORD = "mbernar910";
                break;
            case 2://Inga
                URL = "jdbc:mysql://localhost:3306/escaperoomdb";
                PASSWORD = "password";
                break;
            case 3://Pau
                URL = "jdbc:mysql://127.0.0.1:3306/escaperoomdb";
                PASSWORD = "";
                break;
        }
    }

    public Connection dbConnect() {
        Connection connection = null;
        if (connectionSetting != -1) {
            setConnectionOption(connectionSetting);
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                logger.info("Connection established successfully.");
                return connection;
            } catch (SQLException e) {
                logger.error("Saved connection setting failed, trying other options.");
            }
        }
        for (int i = 0; i < MAX_CONNECTION_OPTIONS; i++) {
            setConnectionOption(i);
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                logger.info("Connection established successfully.");
                connectionSetting = i;
                break;
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