package org.example.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.CLASESTESTS.PlayerTEST;

import java.sql.*;
import java.util.ArrayList;

public class SqlPlayerRepository {
    Logger logger = LogManager.getLogger(SqlPlayerRepository.class);
    private DatabaseConnection dbConnection;

    public SqlPlayerRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public boolean isDuplicatePlayer(String playerName) {
        String sql = "SELECT COUNT(*) FROM player WHERE Player_name = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, playerName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to check for duplicate Player: ", e);
        }
        return false;
    }

    public void createPlayer(PlayerTEST playerTEST) {
        if (!isDuplicatePlayer(playerTEST.getName())) {
            String sql = "INSERT INTO player (Player_id, Player_name, Player_email, Player_consentNotif, Player_deleted)" +
                    " VALUES (?, ?, ?, ?, ?)";
            try (Connection connection = dbConnection.dbConnect();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, playerTEST.getId());
                statement.setString(2, playerTEST.getName());
                statement.setString(3, playerTEST.getEmail());
                statement.setInt(4, playerTEST.getConsentNotif());
                statement.setInt(5, playerTEST.isDeleted());
                statement.executeUpdate();
                logger.info("Player created.");
                dbConnection.closeConnection(connection);
            } catch (SQLException e) {
                logger.error("Failed to create Player: ", e);
            }
        } else {
            logger.warn("Duplicate Player entry detected: " + playerTEST.getName());
        }
    }

    public PlayerTEST getPlayerById(int id) {
        PlayerTEST playerTEST = null;
        String sql = "SELECT * FROM player WHERE Player_id = ? AND Player_deleted = 0";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("Player_name");
                    String email = resultSet.getString("Player_email");
                    int consentNotif = resultSet.getInt("Player_consentNotif");
                    int deleted = resultSet.getInt("Player_deleted");
                    playerTEST = new PlayerTEST(name, email, consentNotif, deleted);
                    playerTEST.setId(id);
                }
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch Player by ID: ", e);
        }
        return playerTEST;
    }

    public ArrayList<PlayerTEST> getAllPlayers() {
        ArrayList<PlayerTEST> playerTESTList = new ArrayList<>();
        String sql = "SELECT * FROM player WHERE Player_deleted = 0 ORDER BY Player_id DESC";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            logger.info("Received Player.");
            while (resultSet.next()){
                int id = resultSet.getInt("Player_id");
                String name = resultSet.getString("Player_name");
                String email = resultSet.getString("Player_email");
                int consentNotif = resultSet.getInt("Player_consentNotif");
                int deleted = resultSet.getInt("Player_deleted");
                PlayerTEST playerTEST = new PlayerTEST(name, email, consentNotif, deleted);
                playerTEST.setId(id);
                playerTESTList.add(playerTEST);
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch Players: ", e);
        }
        return playerTESTList;
    }

    public void updatePlayer(PlayerTEST playerTEST) {
        String sql = "UPDATE player SET Player_name = ?, Player_email = ?, Player_consentNotif = ?, Player_deleted = ? WHERE Player_id = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, playerTEST.getName());
            statement.setString(2, playerTEST.getEmail());
            statement.setInt(3, playerTEST.getConsentNotif());
            statement.setInt(4, playerTEST.isDeleted());
            statement.setInt(5, playerTEST.getId());
            statement.executeUpdate();
            logger.info("Player updated.");
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to update Player: ", e);
        }
    }
}