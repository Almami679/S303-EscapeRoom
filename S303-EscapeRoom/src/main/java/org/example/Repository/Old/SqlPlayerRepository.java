package org.example.Repository.Old;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class SqlPlayerRepository {
    static Logger logger = LogManager.getLogger(SqlPlayerRepository.class);
    private static DatabaseConnection dbConnection;

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

    public void createPlayer(Player player) {
        if (!isDuplicatePlayer(player.getName())) {
            String sql = "INSERT INTO player (Player_id, Player_name, Player_email, Player_consentNotif, Player_deleted)" +
                    " VALUES (?, ?, ?, ?, ?)";
            try (Connection connection = dbConnection.dbConnect();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, player.getId());
                statement.setString(2, player.getName());
                statement.setString(3, player.getEmail());
                statement.setInt(4, player.getConsentNotif());
                statement.setInt(5, player.getDeleted());
                statement.executeUpdate();
                logger.info("Player created.");
                dbConnection.closeConnection(connection);
            } catch (SQLException e) {
                logger.error("Failed to create Player: ", e);
            }
        } else {
            logger.warn("Duplicate Player entry detected: " + player.getName());
        }
    }

    public static Player getPlayerById(int id) {
        Player player = null;
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
                    player = new Player(name, email, consentNotif);
                    player.setId(id);
                }
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch Player by ID: ", e);
        }
        return player;
    }

    public ArrayList<Player> getAllPlayers() {
        ArrayList<Player> playerList = new ArrayList<>();
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
                Player player = new Player(name, email, consentNotif);
                player.setId(id);
                playerList.add(player);
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch Players: ", e);
        }
        return playerList;
    }


    public void updatePlayer(Player player) {
        String sql = "UPDATE player SET Player_name = ?, Player_email = ?, Player_consentNotif = ?, Player_deleted = ? WHERE Player_id = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, player.getName());
            statement.setString(2, player.getEmail());
            statement.setInt(3, player.getConsentNotif());
            statement.setInt(4, player.getDeleted());
            statement.setInt(5, player.getId());
            statement.executeUpdate();
            logger.info("Player updated.");
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to update Player: ", e);
        }
    }

    /// -> AÑADIR CONEXIONES DE DB PARA COMPLETEDGAMES Y PLAYERSALES;
}