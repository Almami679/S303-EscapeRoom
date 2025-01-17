package org.example.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Entity;
import org.example.Repository.Common.DatabaseConnection;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Serializer {
    private static Logger logger = LogManager.getLogger(RepositoryImpl.class);

    public Entity deserialize(String query,
                                         EntityAttributes entity,
                                         DatabaseConnection dbConnection) throws SQLException {

        return null;
    }

    public static void serialize(String query,
                                 EntityAttributes entity,
                                 DatabaseConnection dbConnection) {
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(query)){
            statement.executeUpdate();
            logger.info(entity.name() + " created.");
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to create " + entity.name() +": ", e);
        }
    }
}