package org.example.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.CLASESTESTS.TipsTEST;

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

    public void addTips(TipsTEST tipsTEST) {
        String sql = "INSERT INTO tips (Tips_id, Tips_text, Tips_Room_id) VALUES (?, ?, ?)";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, tipsTEST.getId());
            statement.setString(2, tipsTEST.getText());
            statement.setInt(3, tipsTEST.getRoomId());
            statement.executeUpdate();
            logger.info("Tip added.");
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to add Tip: ", e);
        }
    }

    public TipsTEST getTipsById(int id) {
        TipsTEST tipsTEST = null;
        String sql = "SELECT * FROM tips WHERE Tips_id = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String text = resultSet.getString("Tips_text");
                    int roomId = resultSet.getInt("Tips_Room_id");
                    tipsTEST = new TipsTEST(text, roomId);
                    tipsTEST.setId(id);
                }
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch Tip by ID: ", e);
        }
        return tipsTEST;
    }

    public ArrayList<TipsTEST> getAllTips() {
        ArrayList<TipsTEST> tipsList = new ArrayList<>();
        String sql = "SELECT * FROM tips ORDER BY Tips_id DESC";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("Tips_id");
                String text = resultSet.getString("Tips_text");
                int roomId = resultSet.getInt("Tips_Room_id");

                TipsTEST tipsTEST = new TipsTEST(text, roomId);
                tipsTEST.setId(id);
                tipsList.add(tipsTEST);
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch Tips: ", e);
        }
        return tipsList;
    }

    public void updateTips(TipsTEST tipsTEST) {
        String sql = "UPDATE tips SET Tips_text = ?, Tips_Room_id = ? WHERE Tips_id = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tipsTEST.getText());
            statement.setInt(2, tipsTEST.getRoomId());
            statement.setInt(3, tipsTEST.getId());
            statement.executeUpdate();
            logger.info("Tip updated.");
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to update Tip: ", e);
        }
    }
}