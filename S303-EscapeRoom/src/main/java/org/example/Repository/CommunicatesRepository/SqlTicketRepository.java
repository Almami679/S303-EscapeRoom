package org.example.Repository.CommunicatesRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Communicates.Ticket;
import org.example.Repository.Common.DatabaseConnection;
import java.sql.*;


public class SqlTicketRepository {
    private static final Logger logger = LogManager.getLogger(SqlTicketRepository.class);
    private static DatabaseConnection dbConnection;

    public SqlTicketRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addTicket(Ticket ticket) {
        String sql = "INSERT INTO ticket (Ticket_id, Ticket_saleId) VALUES (?, ?, ?)";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ticket.getId());
            statement.setInt(2, ticket.getSale().getId());
            statement.setString(3, ticket.getText());
            statement.executeUpdate();
            logger.info("Ticket added.");
        } catch (SQLException e) {
            logger.error("Failed to add Ticket: ", e);
        }
    }

    public static Ticket getTicketById(int id) {
        Ticket ticket = null;
        String sql = "SELECT * FROM ticket WHERE Ticket_id = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int saleId = resultSet.getInt("Ticket_saleId");
                    String text = resultSet.getString("Notification_text");
                    int playerId = resultSet.getInt("Notification_playerId");
                    ticket = new Ticket(id, playerId, saleId, text);
                }
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch Notification by ID: " + id, e);
        }
        return ticket;
    }


    public void updateTicket(Ticket ticket) {
        String sql = "UPDATE ticket SET Ticket_text = ?, Ticket_saleId = ? WHERE Ticket_id = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ticket.getText());
            statement.setInt(2, ticket.getSale().getId());
            statement.setInt(3, ticket.getId());
            statement.executeUpdate();
            logger.info("Ticket updated.");
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to update Ticket: ", e);
        }
    }
}