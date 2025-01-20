package org.example.Repository.Old;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Entities.GameEntities.Game;
import org.example.Repository.Common.DatabaseConnection;

import java.sql.*;
import java.time.Instant;

public class SqlGameRepository {

    static Logger logger = LogManager.getLogger(SqlPlayerRepository.class);
    private static DatabaseConnection dbConnection;

    public SqlGameRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addGame(Game game) {
        String sql = "INSERT INTO game (Game_id, Game_date, Game_escapeRoomId, Game_deleted)" +
                " VALUES (?, ?, ?, ?)";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, game.getId());
            statement.setInt(2, game.getEscapeRoom().getId());
            statement.setInt(3, game.getDeleted());
            statement.executeUpdate();
            logger.info("Gift created.");
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to create Gift: ", e);
        }
    }

    //TODO el metodo new Game() aqui, para obtener un juego no tiene sentido.
    // esto esta creando el juego y luego esta cambiando el id con setId() (esta parte no me queda claro)
    // y nosotros necesitoamos obtener a base de busqueda de ids
    public static Game getGameById(int id) {
        Game game = null;
        String sql = "SELECT * FROM game WHERE Game_id = ? AND Game_deleted = 0";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int escapeRoomId = resultSet.getInt("Game_escapeRoomId");
                    int finished = resultSet.getInt("Game_finished");
                    int deleted = resultSet.getInt("Game_deleted");
                    Timestamp createdAt = resultSet.getTimestamp(Instant.now().getNano());
                    Timestamp updatedAt = resultSet.getTimestamp(Instant.now().getNano());
                    game = new Game(id, escapeRoomId, createdAt, updatedAt, finished, deleted);
                    game.setId(id);
                }
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch Player by ID: ", e);
        }
        return game;
    }
}
