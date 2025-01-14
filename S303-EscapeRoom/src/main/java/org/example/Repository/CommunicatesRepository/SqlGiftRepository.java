package org.example.Repository.CommunicatesRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Communicates.Certificate;
import org.example.Modules.Communicates.Gift;
import org.example.Repository.DatabaseConnection;
import org.example.Repository.SqlPlayerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlGiftRepository {
    Logger logger = LogManager.getLogger(SqlPlayerRepository.class);
    private DatabaseConnection dbConnection;

    public SqlGiftRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addGift(Gift gift) {
        String sql = "INSERT INTO gift (Gift_id, Gift_gameId, Gift_text)" +
                " VALUES (?, ?, ?)";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, gift.getId());
            statement.setInt(2, gift.getGame().getId());
            statement.setString(3, gift.getText() + "|||" + gift.getDiscountKey());
            statement.executeUpdate();
            logger.info("Gift created.");
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to create Gift: ", e);
        }
    }

    public static Certificate getGiftById(int id) {
        Gift gift = null;
        String sql = "SELECT * FROM gift WHERE Gift_id = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int gameId = resultSet.getInt("Certificate_gameId");
                    int playerId = resultSet.getInt("Certificate_playerId");
                    String text = resultSet.getString("Certificate_text");
                    certificate = new Certificate(id, gameId, playerId, text);
                }
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch Player by ID: ", e);
        }
        return certificate;
    }
}
