package org.example.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.CLASESTESTS.PlayerTEST;
import org.example.Modules.CLASESTESTS.SaleTEST;

import java.sql.*;

public class SqlSaleRepository {
    static Logger logger = LogManager.getLogger(SqlPlayerRepository.class);
    private static DatabaseConnection dbConnection;

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

    public static SaleTEST getSaleById(int id) {
        SaleTEST saleTEST = null;
        String sql = "SELECT * FROM player WHERE Player_id = ? AND Player_deleted = 0";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int saleId = resultSet.getInt("Sale_id");
                    Timestamp date = resultSet.getTimestamp("Sale_date");
                    double price = resultSet.getInt("Sale_price");
                    int gameId = resultSet.getInt("Sale_gameId");
                    int deleted = resultSet.getInt("Sale_deleted");
                    saleTEST = new SaleTEST(saleId, date, price, gameId, deleted);
                    saleTEST.setId(id);
                }
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch Player by ID: ", e);
        }
        return saleTEST;
    }
}
