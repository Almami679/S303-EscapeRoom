package org.example.Repository.Old;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Entities.RoomEntities.Room;
import org.example.Repository.Common.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class SqlRoomRepository {
    Logger logger = LogManager.getLogger(SqlRoomRepository.class);
    private DatabaseConnection dbConnection;

    public SqlRoomRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
    public boolean isDuplicateRoom(String roomName) {
        String sql = "SELECT COUNT(*) FROM room WHERE Room_name = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, roomName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to check for duplicate Room: ", e);
        }
        return false;
    }


    public void addRoom(Room room) {
        if (!isDuplicateRoom(room.getName())) {
            String sql = "INSERT INTO room (Room_id, Room_name, Room_difficulty, Room_price, Room_escapeRoomid, Room_deleted, Room_createdAt, Room_updatedAt)" +
                    " VALUES (?, ?, ?, ?, ?, 0, ?, ?)";
            try (Connection connection = dbConnection.dbConnect();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, room.getId());
                statement.setString(2, room.getName());
                statement.setString(3, room.getDifficulty());
                statement.setDouble(4, room.getPrice());
                statement.setTimestamp(5, room.getCreatedAt());
                statement.setTimestamp(6, room.getUpdatedAt());
                statement.executeUpdate();
                logger.info("Room added.");
                dbConnection.closeConnection(connection);
            } catch (SQLException e) {
                logger.error("Failed to add Room: ", e);
            }
        } else {
            logger.warn("Duplicate Room entry detected: " + room.getName());
        }
    }

    public Room getRoomById(int id) {
        Room room = null;
        String sql = "SELECT * FROM room WHERE Room_id = ? AND Room_deleted = 0";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("Room_name");
                    String difficulty = resultSet.getString("Room_difficulty");
                    double price = resultSet.getDouble("Room_price");
                    int escapeRoomId = resultSet.getInt("Room_escapeRoomid");
                    int deleted = resultSet.getInt("Room_deleted");
                    Timestamp createdAt = resultSet.getTimestamp("Room_createdAt");
                    Timestamp updatedAt = resultSet.getTimestamp("Room_updatedAt");
                    room = new Room(name, difficulty, price);
                    room.setId(id);
                }
                dbConnection.closeConnection(connection);
            }
        } catch (SQLException e) {
            logger.error("Failed to fetch Room by ID: ", e);
        }
        return room;
    }

    public ArrayList<Room> getAllRooms() {
        ArrayList<Room> roomList = new ArrayList<>();
        String sql = "SELECT * FROM room WHERE Room_deleted = 0 ORDER BY Room_id DESC";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            logger.info("Received Room.");
            while (resultSet.next()){
                int id = resultSet.getInt("Room_id");
                String name = resultSet.getString("Room_name");
                String difficulty = resultSet.getString("Room_difficulty");
                double price = resultSet.getDouble("Room_price");
                int escapeRoomId = resultSet.getInt("Room_escapeRoomid");
                int deleted = resultSet.getInt("Room_deleted");
                Timestamp createdAt = resultSet.getTimestamp("Room_createdAt");
                Timestamp updatedAt = resultSet.getTimestamp("Room_updatedAt");
                Room room = new Room(name, difficulty, price);
                room.setId(id);
                roomList.add(room);
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch Room: ", e);
        }
        return roomList;
    }

    public void roomUpdate(Room room) {
        String sql = "UPDATE room SET Room_name = ?, Room_difficulty = ?, Room_price = ?, Room_escapeRoomid = ?, Room_deleted = ?, Room_updatedAt = ? WHERE Room_id = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, room.getName());
            statement.setString(2, room.getDifficulty());
            statement.setDouble(3, room.getPrice());
            statement.setTimestamp(4, room.getUpdatedAt());
            statement.setInt(5, room.getId());
            statement.executeUpdate();
            logger.info("Room updated.");
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to update Room: ", e);
        }
    }
}