package org.example.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.CLASESTESTS.SaleTEST;
import org.example.Modules.Communicates.Gift;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
