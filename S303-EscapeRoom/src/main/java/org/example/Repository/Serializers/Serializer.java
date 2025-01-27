package org.example.Repository.Serializers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Entities.Entity;
import org.example.Repository.Common.DatabaseConnection;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.example.Repository.Serializers.EntityConstructorsSql.*;


public class Serializer {
    private static Logger logger = LogManager.getLogger(RepositoryImpl.class);

    public static DatabaseConnection dbConnection = new DatabaseConnection();

    public static Map<String, Object> deserialize(ResultSet resultSet, EntityAttributes entityEnum) throws SQLException {
        Map<String, Object> entityData = new HashMap<>();
        //logger.info("Deserializando ResultSet...");
        for (String attribute : entityEnum.getAttributes()) {
            try {
                Object value = resultSet.getObject(attribute);
                entityData.put(attribute, value);
            } catch (SQLException e) {
                logger.info("Error al obtener el valor de la columna " + attribute + ": " + e.getMessage());
            }
        }

        return entityData;
    }

    public static Entity deserialize(String query, EntityAttributes entityEnum) throws SQLException {
        Entity entity;
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                Map<String, Object> entityData = deserialize(resultSet, entityEnum);
                entity = createEntityToDeserialize(entityEnum, entityData);
                return entity;
            } else {
                throw new SQLException("No data found for the given ID.");
            }
        } catch (SQLException e) {
            logger.error("Error during deserialization: ", e);
            throw e;
        }
    }

    public static ArrayList<Entity> deserializeGetAll(String query, EntityAttributes entityEnum) throws SQLException {
        ArrayList<Entity> entities = new ArrayList<>();
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Map<String, Object> entityData = deserialize(resultSet, entityEnum);
                entities.add(createEntityToDeserialize(entityEnum, entityData));
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
            dbConnection.closeConnection(connection);
            //logger.info(entity.name() + " serialized.");
        } catch (SQLException e) {
            logger.error("Failed to serialize " + entity.name() + ": ", e);
            // Puedes manejar la excepción aquí, por ejemplo, lanzar una excepción personalizada
            throw new RuntimeException("Serialization failed for " + entity.name(), e);
        }
    }

    public static Entity createEntityToDeserialize(EntityAttributes entityEnum, Map<String, Object> entityData) throws SQLException {
        Entity entity;
        // Usamos los atributos que corresponden al enum para acceder a los datos
        ArrayList<String> attributes = entityEnum.getAttributes();

        // Verificamos que el HashMap no esté vacío
        if (!entityData.isEmpty()) {
            switch (entityEnum) {
                case gift -> {
                    entity = giftConstructor(entityData, attributes);
                }
                case ticket -> {
                    entity = ticketConstructor(entityData, attributes);
                }
                case certificate -> {
                    entity = certificateConstructor(entityData, attributes);
                }
                case notification -> {
                    entity = notificationConstructor(entityData, attributes);
                }
                case game -> {
                    entity = gameConstructor(entityData, attributes);
                }
                case room -> {
                    entity = roomConstructor(entityData, attributes);
                }
                case sale -> {
                    entity = saleConstructor(entityData, attributes);
                }
                case tips -> {
                    entity = tipsConstructor(entityData, attributes);
                }
                case player -> {
                    entity = playerConstructor(entityData, attributes);
                }
                case objectdeco -> {
                    entity = objectDecoConstructor(entityData, attributes);
                }
                case escaperoom -> {
                    entity = escapeRoomConstructor(entityData, attributes);
                }
                case room_has_objectdeco -> {
                    entity = roomHasObjectConstructor(entityData, attributes);
                }
                case room_has_tips -> {
                    entity = roomHasTipsConstructor(entityData, attributes);
                }
                case game_has_player -> {
                    entity = gameHasPlayerConstructor(entityData, attributes);
                }
                case escaperoom_has_room -> {
                    entity = escapeRoomHasRoomConstructor(entityData, attributes);
                }
                default -> throw new SQLException("Unsupported entity type");
            }
        } else {
            throw new SQLException("No data found in the provided map");
        }
        return entity;
    }
}