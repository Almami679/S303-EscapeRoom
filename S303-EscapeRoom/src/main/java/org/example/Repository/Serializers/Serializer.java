package org.example.Repository.Serializers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Entities.Entity;
import org.example.Repository.Common.DatabaseConnection;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.*;
import java.util.ArrayList;

import static org.example.Repository.Serializers.EntityConstructorsSql.*;


public class Serializer {
    private static Logger logger = LogManager.getLogger(RepositoryImpl.class);

    public static DatabaseConnection dbConnection = new DatabaseConnection();


    public static Entity deserialize(
            String query,
            EntityAttributes entityEnum) throws SQLException {
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                return createEntityToDeserialize(entityEnum, resultSet);
            } catch (SQLException e) {
                logger.error("Failed to deserialize entity: ", e);
                throw e;
            }
        }
    }

    public static ArrayList<Entity> deserializeGetAll(String query, EntityAttributes entityEnum) throws SQLException {
        ArrayList<Entity> entities = new ArrayList<>();
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                entities.add(createEntityToDeserialize(entityEnum, resultSet));
            }
        } catch (SQLException e) {
            logger.error("Failed to deserialize entity: ", e);
            throw e;
        }
        return entities;
    }

    public static void serializeUpdate(String query, EntityAttributes enumAttributes, String action, ArrayList<String> attributes) {
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            // Ejecuta la consulta generada
            int affectedRows = stmt.executeUpdate();

            // Verifica si hubo filas afectadas
            if (affectedRows == 0) {
                logger.warn("No rows affected for action: " + action);
            } else {
                logger.info("Query executed successfully. Affected rows: " + affectedRows);
            }

            // Confirma la transacción si es necesario
            if (!connection.getAutoCommit()) {
                connection.commit();
            }

        } catch (SQLException e) {
            logger.error("Error during serialization: " + e.getMessage());
            try {
                if (!dbConnection.dbConnect().getAutoCommit()) {
                    dbConnection.dbConnect().rollback();
                }
            } catch (SQLException rollbackEx) {
                logger.error("Error during rollback: " + rollbackEx.getMessage());
            }
        }
    }


    public static void serialize(String query, EntityAttributes entity, String action, ArrayList<String> values) {
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < values.size(); i++) {
                statement.setString(i+1, values.get(i));
            }
            statement.executeUpdate();
            logger.info(entity.name() + " " + action + "d.");
            dbConnection.closeConnection(connection);
            logger.info(entity.name() + " serialized.");
        } catch (SQLException e) {
            logger.error("Failed to serialize " + entity.name() + ": ", e);
        }
    }

    private static Entity createEntityToDeserialize(EntityAttributes entityEnum, ResultSet resultSet) throws SQLException {
        Entity entity = null;
        ArrayList<String> attributes = entityEnum.getAttributes();
        if (resultSet.next()) {

            switch (entityEnum) {
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
                case game -> {
                    entity = gameConstructor(resultSet, attributes);
                }
                case room -> {
                    entity = roomConstructor(resultSet, attributes);
                }
                case sale -> {
                    entity = saleConstructor(resultSet, attributes);
                }
                case tips -> {
                    entity = tipsConstructor(resultSet, attributes);
                }
                case player -> {
                    entity = playerConstructor(resultSet, attributes);
                }
                case objectdeco -> {
                    entity = objectDecoConstructor(resultSet, attributes);
                }
                case escaperoom -> {
                    entity = escapeRoomConstructor(resultSet, attributes);
                }
            }
            /// Seguir haciendo los casos de las entities con las clases definitivas y sus respectivos
            /// constructores

        }
        return entity;
    }
}