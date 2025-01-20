package org.example.Repository.Old;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Entities.CLASESTESTS.GameTEST;
import org.example.Repository.Common.DatabaseConnection;

import java.sql.*;

public class SqlGameRepository {

    static Logger logger = LogManager.getLogger(SqlPlayerRepository.class);
    private static DatabaseConnection dbConnection;

    public SqlGameRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    /*public void addGame(GameTEST game) {
        String sql = "INSERT INTO game (Game_id, Game_date, Game_escapeRoomId, Game_deleted)" +
                " VALUES (?, ?, ?, ?)";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, game.getId());
            statement.setTimestamp(2, game.getGameDate());
            statement.setInt(3, game.getEscapeRoom().getId());
            statement.setInt(4, game.getDeleted());
            statement.executeUpdate();
            logger.info("Gift created.");
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to create Gift: ", e);
        }
    }

    public static GameTEST getGameById(int id) {
        GameTEST gameTEST = null;
        String sql = "SELECT * FROM game WHERE Game_id = ? AND Game_deleted = 0";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Timestamp date = resultSet.getTimestamp("Game_date");
                    int escapeRoomId = resultSet.getInt("Game_escapeRoomId");
                    int finished = resultSet.getInt("Game_finished");
                    int deleted = resultSet.getInt("Game_deleted");
                    gameTEST = new GameTEST(date, escapeRoomId, finished, deleted);
                    gameTEST.setId(id);
                }
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch Player by ID: ", e);
        }
        return gameTEST;
    }*/
}
