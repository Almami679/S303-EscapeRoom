package org.example.Repository.Old;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Entities.RoomEntities.Tips;
import org.example.Repository.Common.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class SqlTipsRepository {
    private static final Logger logger = LogManager.getLogger(SqlTipsRepository.class);
    private final DatabaseConnection dbConnection;

    public SqlTipsRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public boolean isDuplicateTips(String tipText) {
        String sql = "SELECT COUNT(*) FROM tips WHERE Tips_text = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tipText);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to check for duplicate Tip: ", e);
        }
        return false;
    }

    public void addTips(Tips tips) {
        String sql = "INSERT INTO tips (Tips_id, Tips_text, Tips_Room_id) VALUES (?, ?, ?)";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, tips.getId());
            statement.setString(2, tips.getText());
            statement.setInt(3, tips.getRoomId());
            statement.executeUpdate();
            logger.info("Tip added.");
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to add Tip: ", e);
        }
    }

    public Tips getTipsById(int id) {
        Tips tips = null;
        String sql = "SELECT * FROM tips WHERE Tips_id = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String text = resultSet.getString("Tips_text");
                    int roomId = resultSet.getInt("Tips_Room_id");
                    int deleted = resultSet.getInt("deleted");
                    tips = new Tips(id, text, roomId, deleted);
                    tips.setId(id);
                }
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch Tip by ID: ", e);
        }
        return tips;
    }

    public ArrayList<Tips> getAllTips() {
        ArrayList<Tips> tipsList = new ArrayList<>();
        String sql = "SELECT * FROM tips ORDER BY Tips_id DESC";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("Tips_id");
                String text = resultSet.getString("Tips_text");
                int roomId = resultSet.getInt("Tips_Room_id");
                int deleted = resultSet.getInt("deleted");

                Tips tips = new Tips(id, text, roomId, deleted);
                tips.setId(id);
                tipsList.add(tips);
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch Tips: ", e);
        }
        return tipsList;
    }

    public void updateTips(Tips tips) {
        String sql = "UPDATE tips SET Tips_text = ?, Tips_Room_id = ? WHERE Tips_id = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tips.getText());
            statement.setInt(2, tips.getRoomId());
            statement.setInt(3, tips.getId());
            statement.executeUpdate();
            logger.info("Tip updated.");
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to update Tip: ", e);
        }
    }
}