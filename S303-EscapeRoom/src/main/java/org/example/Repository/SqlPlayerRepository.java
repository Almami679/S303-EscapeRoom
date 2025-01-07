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

    public void createPlayer(PlayerTEST playerTEST) {
        String sql = "INSERT INTO player (Player_id, Player_name, Player_email, Player_consentNotif, Player_delete)" +
                " VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, playerTEST.getId());
            statement.setString(2, playerTEST.getName());
            statement.setString(3, playerTEST.getEmail());
            statement.setBoolean(4, playerTEST.getConsentNotif());
            statement.setBoolean(5, playerTEST.isDeleted());
            statement.executeUpdate();
            logger.info("Player created.");
        } catch (SQLException e) {
            logger.error("Failed to create Player: ", e);
        }
    }

    public PlayerTEST getPlayerById(int id) {
        PlayerTEST playerTEST = null;
        String sql = "SELECT * FROM player WHERE Player_id = ? AND Player_delete = 0";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("Player_name");
                    String email = resultSet.getString("Player_email");
                    boolean consentNotif = resultSet.getBoolean("Player_consentNotif");
                    boolean deleted = resultSet.getBoolean("Player_delete");
                    playerTEST = new PlayerTEST(name, email, consentNotif, deleted);
                    playerTEST.setId(id);
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to fetch Player by ID: ", e);
        }
        return playerTEST;
    }

    public ArrayList<PlayerTEST> getAllPlayers() {
        ArrayList<PlayerTEST> playerTESTList = new ArrayList<>();
        String sql = "SELECT * FROM player WHERE Player_delete = 0 ORDER BY Player_id DESC";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            logger.info("Received Player.");
            while (resultSet.next()) {
                int id = resultSet.getInt("Player_id");
                String name = resultSet.getString("Player_name");
                String email = resultSet.getString("Player_email");
                boolean consentNotif = resultSet.getBoolean("Player_consentNotif");
                boolean deleted = resultSet.getBoolean("Player_delete");
                PlayerTEST playerTEST = new PlayerTEST(name, email, consentNotif, deleted);
                playerTEST.setId(id);
                playerTESTList.add(playerTEST);
            }
        } catch (SQLException e) {
            logger.error("Failed to fetch Players: ", e);
        }
        return playerTESTList;
    }

    public void updatePlayer(PlayerTEST playerTEST) {
        String sql = "UPDATE player SET Player_name = ?, Player_email = ?, Player_consentNotif = ?, Player_delete = ? WHERE Player_id = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, playerTEST.getName());
            statement.setString(2, playerTEST.getEmail());
            statement.setBoolean(3, playerTEST.getConsentNotif());
            statement.setBoolean(4, playerTEST.isDeleted());
            statement.setInt(5, playerTEST.getId());
            statement.executeUpdate();
            logger.info("Player updated.");
        } catch (SQLException e) {
            logger.error("Failed to update Player: ", e);
        }
    }
}