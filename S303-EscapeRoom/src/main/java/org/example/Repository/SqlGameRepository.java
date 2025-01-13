package org.example.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.CLASESTESTS.GameTEST;
import org.example.Modules.Communicates.Gift;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlGameRepository {

    Logger logger = LogManager.getLogger(SqlPlayerRepository.class);
    private DatabaseConnection dbConnection;

    public SqlGameRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addGame(GameTEST game) {
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
}
