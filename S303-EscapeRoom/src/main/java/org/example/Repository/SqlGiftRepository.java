package org.example.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.CLASESTESTS.SaleTEST;
import org.example.Modules.Communicates.Gift;
import org.example.Modules.Communicates.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<Gift> getAllGifts(){
        ArrayList<Gift> giftList = new ArrayList<>();
        String sql = "SELECT * FROM gift";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("Gift_id");
                int gameId = resultSet.getInt("Gift_gameId");
                String text = resultSet.getString("Gift_text");
                /*Gift gift = new Gift(gameId,text);
                gift.setId(id);                             //Descomentar cuando esté la clase Gift
                giftList.add(gift);*/
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch Gifts: ", e);
        }
        return giftList;
    }

    public Gift getGiftById(int id) {
        Gift gift = null;
        String sql = "SELECT * FROM gift WHERE Gift_id = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idG = resultSet.getInt("Gift_id");
                    int gameId = resultSet.getInt("Gift_gameId");
                    String text = resultSet.getString("Gift_text");
                    /*gift = new Gift(gameId, text); //Descomentar cuando esté la clase Gift
                    gift.setId(idG);*/
                }
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch Gift by ID: ", e);
        }
        return gift;
    }

    public void updateGift(Gift gift) {
        String sql = "UPDATE gift SET Gift_gameId = ?, Gift_text = ?, Gift_playerId = ? WHERE Gift_id = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, gift.getGame().getId());
            statement.setString(2, gift.getText());
            statement.setInt(3, gift.getPlayer().getId());
            logger.info("Ticket updated.");
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to update Gift: ", e);
        }
    }
}
