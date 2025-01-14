package org.example.Repository.Old;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.CLASESTESTS.EscapeRoomTEST;
import org.example.Repository.Common.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class SqlEscapeRoomRepository {
    Logger logger = LogManager.getLogger(SqlEscapeRoomRepository.class);
    private DatabaseConnection dbConnection;

    public SqlEscapeRoomRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public boolean isDuplicateEscapeRoom(String escapeRoomName) {
        String sql = "SELECT COUNT(*) FROM escaperoom WHERE EscapeRoom_name = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, escapeRoomName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to check for duplicate EscapeRoom: ", e);
        }
        return false;
    }

    public void addEscapeRoom(EscapeRoomTEST escapeRoomTEST) {
        String sql = "INSERT INTO escaperoom (EscapeRoom_id, EscapeRoom_name, EscapeRoom_price, EscapeRoom_theme, EscapeRoom_deleted, EscapeRoom_createdAt, EscapeRoom_updatedAt)" +
                " VALUES (?, ?, ?, ?, 0, ?, ?)";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, escapeRoomTEST.getId());
            statement.setString(2, escapeRoomTEST.getName());
            statement.setDouble(3, escapeRoomTEST.getPrice());
            statement.setString(4, escapeRoomTEST.getTheme());
            statement.setTimestamp(5, escapeRoomTEST.getCreated_at());
            statement.setTimestamp(6, escapeRoomTEST.getUpdated_at());
            statement.executeUpdate();
            logger.info("EscapeRoom added.");
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to add EscapeRoom: ", e);
        }
    }

    public EscapeRoomTEST getEscapeRoomById(int id) {
        EscapeRoomTEST escapeRoomTEST = null;
        String sql = "SELECT * FROM escaperoom WHERE EscapeRoom_id = ? AND EscapeRoom_deleted = 0";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("EscapeRoom_name");
                    double price = resultSet.getDouble("EscapeRoom_price");
                    String theme = resultSet.getString("EscapeRoom_theme");
                    int deleted = resultSet.getInt("EscapeRoom_deleted");
                    Timestamp createdAt = resultSet.getTimestamp("EscapeRoom_createdAt");
                    Timestamp updatedAt = resultSet.getTimestamp("EscapeRoom_updatedAt");
                    escapeRoomTEST = new EscapeRoomTEST(name, price, theme, deleted, createdAt, updatedAt);
                    escapeRoomTEST.setId(id);
                }
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch EscapeRoom by ID: ", e);
        }
        return escapeRoomTEST;
    }

    public ArrayList<EscapeRoomTEST> getAllEscapeRooms() {
        ArrayList<EscapeRoomTEST> escapeRoomTESTList = new ArrayList<>();
        String sql = "SELECT * FROM escaperoom WHERE EscapeRoom_deleted = 0 ORDER BY EscapeRoom_id DESC";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            logger.info("Received EscapeRoom.");
            while (resultSet.next()){
                int id = resultSet.getInt("EscapeRoom_id");
                String name = resultSet.getString("EscapeRoom_name");
                double price = resultSet.getDouble("EscapeRoom_price");
                String theme = resultSet.getString("EscapeRoom_theme");
                int deleted = resultSet.getInt("EscapeRoom_deleted");
                Timestamp createdAt = resultSet.getTimestamp("EscapeRoom_createdAt");
                Timestamp updatedAt = resultSet.getTimestamp("EscapeRoom_updatedAt");
                EscapeRoomTEST escapeRoomTEST = new EscapeRoomTEST(name, price, theme, deleted, createdAt, updatedAt);
                escapeRoomTEST.setId(id);
                escapeRoomTESTList.add(escapeRoomTEST);
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch EscapeRoom: ", e);
        }
        return escapeRoomTESTList;
    }

    public void escapeRoomUpdate(EscapeRoomTEST escapeRoomTEST) {
        String sql = "UPDATE escaperoom SET EscapeRoom_name = ?, EscapeRoom_price = ?, EscapeRoom_theme = ?, EscapeRoom_deleted = ?, EscapeRoom_updatedAt = ? WHERE EscapeRoom_id = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, escapeRoomTEST.getName());
            statement.setDouble(2, escapeRoomTEST.getPrice());
            statement.setString(3, escapeRoomTEST.getTheme());
            statement.setInt(4, escapeRoomTEST.isDeleted());
            statement.setTimestamp(5, escapeRoomTEST.getUpdated_at());
            statement.setInt(6, escapeRoomTEST.getId());
            statement.executeUpdate();
            logger.info("EscapeRoom with ID: " + escapeRoomTEST.getId()+ " updated.");
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to update EscapeRoom: ", e);
        }
    }
}