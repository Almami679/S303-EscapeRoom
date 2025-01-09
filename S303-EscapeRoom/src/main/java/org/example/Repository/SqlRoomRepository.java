package org.example.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.CLASESTESTS.RoomTEST;

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
        } catch (SQLException e) {
            logger.error("Failed to check for duplicate Room: ", e);
        }
        return false;
    }


    public void addRoom(RoomTEST roomTEST) {
        if (!isDuplicateRoom(roomTEST.getName())) {
            String sql = "INSERT INTO room (Room_id, Room_name, Room_difficulty, Room_price, Room_escapeRoomid, Room_deleted, Room_createdAt, Room_updatedAt)" +
                    " VALUES (?, ?, ?, ?, ?, 0, ?, ?)";
            try (Connection connection = dbConnection.dbConnect();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, roomTEST.getId());
                statement.setString(2, roomTEST.getName());
                statement.setString(3, roomTEST.getDifficulty());
                statement.setDouble(4, roomTEST.getPrice());
                statement.setInt(5, roomTEST.getEscapeRoomId());
                statement.setTimestamp(6, roomTEST.getCreated_at());
                statement.setTimestamp(7, roomTEST.getUpdated_at());
                statement.executeUpdate();
                logger.info("Room added.");
            } catch (SQLException e) {
                logger.error("Failed to add Room: ", e);
            }
        } else {
            logger.warn("Duplicate Room entry detected: " + roomTEST.getName());
        }
    }

    public RoomTEST getRoomById(int id) {
        RoomTEST roomTEST = null;
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
                    roomTEST = new RoomTEST(name, difficulty, price, escapeRoomId, deleted, createdAt, updatedAt);
                    roomTEST.setId(id);
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to fetch Room by ID: ", e);
        }
        return roomTEST;
    }

    public ArrayList<RoomTEST> getAllRooms() {
        ArrayList<RoomTEST> roomTESTList = new ArrayList<>();
        String sql = "SELECT * FROM room WHERE Room_deleted = 0 ORDER BY Room_id DESC";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            logger.info("Received Room.");
            do{
                int id = resultSet.getInt("Room_id");
                String name = resultSet.getString("Room_name");
                String difficulty = resultSet.getString("Room_difficulty");
                double price = resultSet.getDouble("Room_price");
                int escapeRoomId = resultSet.getInt("Room_escapeRoomid");
                int deleted = resultSet.getInt("Room_deleted");
                Timestamp createdAt = resultSet.getTimestamp("Room_createdAt");
                Timestamp updatedAt = resultSet.getTimestamp("Room_updatedAt");
                RoomTEST roomTEST = new RoomTEST(name, difficulty, price, escapeRoomId, deleted, createdAt, updatedAt);
                roomTEST.setId(id);
                roomTESTList.add(roomTEST);
            }while (resultSet.next());
        } catch (SQLException e) {
            logger.error("Failed to fetch Room: ", e);
        }
        return roomTESTList;
    }

    public void roomUpdate(RoomTEST roomTEST) {
        String sql = "UPDATE room SET Room_name = ?, Room_difficulty = ?, Room_price = ?, Room_escapeRoomid = ?, Room_deleted = ?, Room_updatedAt = ? WHERE Room_id = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, roomTEST.getName());
            statement.setString(2, roomTEST.getDifficulty());
            statement.setDouble(3, roomTEST.getPrice());
            statement.setInt(4, roomTEST.getEscapeRoomId());
            statement.setInt(5, roomTEST.isDeleted());
            statement.setTimestamp(6, roomTEST.getUpdated_at());
            statement.setInt(7, roomTEST.getId());
            statement.executeUpdate();
            logger.info("Room updated.");
        } catch (SQLException e) {
            logger.error("Failed to update Room: ", e);
        }
    }
}