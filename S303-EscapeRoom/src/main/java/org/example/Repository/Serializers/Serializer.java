package org.example.Repository.Serializers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Entities.CommunicatesEntities.Certificate;
import org.example.Modules.Entities.CommunicatesEntities.Notification;
import org.example.Modules.Entities.Entity;
import org.example.Repository.Common.DatabaseConnection;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.*;
import java.util.ArrayList;

import static org.example.Repository.Serializers.EntityConstructorsSql.*;


public class Serializer {
    private static Logger logger = LogManager.getLogger(RepositoryImpl.class);

    public static Entity deserialize(
            String query,
            EntityAttributes entityEnum,
            DatabaseConnection dbConnection) throws SQLException {
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                return createEntityToDeserialize(entityEnum, resultSet);

            } catch (SQLException e) {
                throw new RuntimeException(e);
                ///acabar logger
            }
        }
    }

    public static void serialize(String query,
                                 EntityAttributes entity,
                                 DatabaseConnection dbConnection,
                                 String action) {
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(query)){
            if(action.equals("add")) {
                statement.executeUpdate();
                logger.info(entity.name() + " created.");

            } else if(action.equals("delete")) {
                statement.executeUpdate();
                logger.info(entity.name() + " deleted.");

            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to serialice " + entity.name() +": ", e);
        }
    }

    private static Entity createEntityToDeserialize(EntityAttributes entityEnum, ResultSet resultSet) throws SQLException {
        Entity entity = null;
        ArrayList<String> attributes = entityEnum.getAttributes();
        switch(entityEnum) {
            case gift -> {
                entity = giftConstructor(resultSet, attributes);
            }
            case ticket -> {
                entity = ticketConstructor(resultSet, attributes);
            }
            case certificate -> {
                entity = certificateConstructor(resultSet, attributes);
            }
            case notification -> {
                entity = notificationConstructor(resultSet, attributes);
            }
            case game ->  {
                entity = gameConstructor(resultSet, attributes);
            }
            case room ->  {
                entity = roomConstructor(resultSet, attributes);
            }
            case sale ->  {
                entity = saleConstructor(resultSet, attributes);
            }
            case tips ->  {
                entity = tipsConstructor(resultSet, attributes);
            }
            case player -> {
                entity = playerConstructor(resultSet, attributes);
            }
            case escaperoom -> {
                entity = escapeRoomConstructor(resultSet, attributes);
            }
            case objectdeco -> {
                entity = objectDecoConstructor(resultSet, attributes);
            }

            /// Seguir haciendo los casos de las entities con las clases definitivas y sus respectivos
            /// constructores

        }

        return entity;
    }
}