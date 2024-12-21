package org.example.Repository;

import org.apache.logging.log4j.Logger;
import org.example.Modules.CLASESTESTS.EscapeRoom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;

public class SqlRoomRepository implements RepositoryMGMT {
    Logger logger = LogManager.getLogger(SqlRoomRepository.class);



    public List<EscapeRoom> getAllEscapeRooms() {
        List<EscapeRoom> escapeRoomList = new ArrayList<>();
        String sql = "SELECT * FROM Room WHERE EscapeRoom_delete = 0 ORDER BY EscapeRoom_id DESC";
        try (Connection connection = DatabaseConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
             logger.info("Received EscapeRoom.");
            do {
                int id = resultSet.getInt("EscapeRoom_id");
                String name = resultSet.getString("EscapeRoom_name");
                double price = resultSet.getDouble("EscapeRoom_name");
                String theme = resultSet.getString("EscapeRoom_theme");
                boolean delete = resultSet.getBoolean("EscapeRoom_deleted");
                Timestamp createdAt = resultSet.getTimestamp("EscapeRoom_createdAt");
                Timestamp updatedAt = resultSet.getTimestamp("EscapeRoom_updatedAt");

                EscapeRoom escapeRoom = new EscapeRoom(name, price, theme, delete, createdAt, updatedAt);
                escapeRoom.setId(id);
                escapeRoomList.add(escapeRoom);
                if (id > EscapeRoom.getLatestId()) {
                    EscapeRoom.setLatestId(id);
                }
            }while (resultSet.next());
        } catch (SQLException e) {
            logger.error("Failed to fetch EscapeRoom: ", e);
        }
        return escapeRoomList;
    }

    public void addEscapeRoom(EscapeRoom escapeRoom) {
        String sql = "INSERT INTO Room (EscapeRoom_id,EscapeRoom_name,EscapeRoom_theme,EscapeRoom_deleted,EscapeRoom_createdAt,EscapeRoom_updatedAt)" +
                " VALUES (?, ?, ?, 0, ?, ?)";
        try (Connection connection = RepositoryMGMT.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, escapeRoom.getId());
            statement.setString(1, escapeRoom.getName());
            statement.setString(3, escapeRoom.getTheme());
            statement.setTimestamp(4, escapeRoom.getCreated_at());
            statement.setTimestamp(5, escapeRoom.getUpdated_at());
            statement.executeUpdate();
            logger.info("EscapeRoom added.");
        } catch (SQLException e) {
            logger.error("Failed to add EscapeRoom: ", e);
        }
    }

}
