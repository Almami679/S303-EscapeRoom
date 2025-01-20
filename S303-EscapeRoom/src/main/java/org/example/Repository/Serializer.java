package org.example.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Entities.CommunicatesEntities.Certificate;
import org.example.Modules.Entities.CommunicatesEntities.Gift;
import org.example.Modules.Entities.CommunicatesEntities.Notification;
import org.example.Modules.Entities.CommunicatesEntities.Ticket;
import org.example.Modules.Entities.Entity;
import org.example.Repository.Common.DatabaseConnection;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.*;
import java.util.ArrayList;

public class Serializer <T>{
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
                if (resultSet.next()) {
                    int id = resultSet.getInt(attributes.get(0));
                    int gameId = resultSet.getInt(attributes.get(1));
                    String text = resultSet.getString(attributes.get(2));
                    String giftKey = resultSet.getString(attributes.get(3));
                    int playerId = resultSet.getInt(attributes.get(4));
                    entity = new Gift(id, gameId, text, playerId, giftKey);
                }
            }
            case ticket -> {
                if (resultSet.next()) {
                    int id = resultSet.getInt(attributes.get(0));
                    int saleId = resultSet.getInt(attributes.get(1));
                    String text = resultSet.getString(attributes.get(2));
                    int playerId = resultSet.getInt(attributes.get(3));
                    Timestamp date = resultSet.getTimestamp(attributes.get(4));
                    entity = new Ticket(id, playerId, saleId, text, date);
                }
            }
            case certificate -> {
                if (resultSet.next()) {
                    int id = resultSet.getInt(attributes.get(0));
                    int gameId = resultSet.getInt(attributes.get(1));
                    String text = resultSet.getString(attributes.get(2));
                    Timestamp created_at = resultSet.getTimestamp(attributes.get(3));
                    int playerId = resultSet.getInt(attributes.get(4));
                    entity = new Certificate(id, gameId, playerId, text, created_at);
                }
            }
            case notification -> {
                if (resultSet.next()) {
                    int id = resultSet.getInt(attributes.get(0));
                    int playerId = resultSet.getInt(attributes.get(1));
                    String text = resultSet.getString(attributes.get(2));
                    Timestamp created_at = resultSet.getTimestamp((attributes.get(3)));
                    entity = new Notification(id, playerId, text, created_at);
                }
            }
            /// Seguir haciendo los casos de las entities con las clases definitivas y sus respectivos
            /// constructores

        }

        return entity;
    }
}