package org.example.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.CLASESTESTS.PlayerTEST;
import org.example.Modules.CLASESTESTS.SaleTEST;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlSaleRepository {
    Logger logger = LogManager.getLogger(SqlPlayerRepository.class);
    private DatabaseConnection dbConnection;

    public SqlSaleRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void createSale(SaleTEST sale) {
        String sql = "INSERT INTO sale (Sale_id, Sale_date, Sale_price, Sale_gameId, Sale_deleted)" +
                " VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sale.getId());
            statement.setTimestamp(2, sale.getDate());
            statement.setDouble(3, sale.getPrice());
            statement.setInt(4, sale.getGameId());
            statement.setInt(5, sale.getDeleted());
            statement.executeUpdate();
            logger.info("Sale created.");
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to create Sale: ", e);
        }
    }
}
