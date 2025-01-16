package org.example.Repository.Common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Repository.Serializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringJoiner;

public class EscapeRoomImp implements Repository<EscapeRoomTEST> {
    private static final Logger logger = LogManager.getLogger(EscapeRoomImp.class);
    private DatabaseConnection dbConnection;
    private Serializer serializer;

    public EscapeRoomImp(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.serializer = new Serializer();
    }

    @Override
    public void add(EscapeRoomTEST escapeRoomTEST, String tableName, ArrayList<Object> attributes) {
        String columns = extractAttributes(attributes);
        String placeholders = generatePlaceholders(attributes.size());
        String sql = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + placeholders + ")";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            attributes.add(escapeRoomTEST.getId());
            attributes.add(escapeRoomTEST.getName());
            attributes.add(escapeRoomTEST.getPrice());
            attributes.add(escapeRoomTEST.getTheme());
            attributes.add(escapeRoomTEST.getDeleted());
            attributes.add(escapeRoomTEST.getCreated_at());
            attributes.add(escapeRoomTEST.getUpdated_at());
            serializer.deserialize(statement, attributes);
            statement.executeUpdate();
            logger.info("EscapeRoom added.");
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to add EscapeRoom: ", e);
        }
    }

    private String extractAttributes(ArrayList<Object> attributes) {
        StringJoiner joiner = new StringJoiner(", ");
        for (Object attribute : attributes) {
            joiner.add(attribute.toString());
        }
        return joiner.toString();
    }

    private String generatePlaceholders(int count) {
        StringJoiner joiner = new StringJoiner(", ");
        for (int i = 0; i < count; i++) {
            joiner.add("?");
        }
        return joiner.toString();
    }

    @Override
    public ArrayList<EscapeRoomTEST> getAll() {
        // Implement getAll method
        return null;
    }

    @Override
    public EscapeRoomTEST getById(int id) {
        // Implement getById method
        return null;
    }

    @Override
    public void delete(int id) {
        // Implement delete method
    }

    @Override
    public void update(EscapeRoomTEST escapeRoomTEST) {
        // Implement update method
    }
}