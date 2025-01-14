package org.example.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.CLASESTESTS.EscapeRoomTEST;
import org.example.Modules.CLASESTESTS.PlayerTEST;
import org.example.Modules.CLASESTESTS.SaleTEST;
import org.example.Modules.Communicates.Ticket;

import java.sql.*;
import java.util.ArrayList;

public class SqlTicketRepository {
    private static final Logger logger = LogManager.getLogger(SqlTicketRepository.class);
    private final DatabaseConnection dbConnection;

    public SqlTicketRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addTicket(Ticket ticket) {
        String sql = "INSERT INTO ticket (Ticket_id, Ticket_saleId, Ticket_text) VALUES (?, ?, ?)";
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

    public ArrayList<Ticket> getAllTickets() {
        ArrayList<Ticket> ticketList = new ArrayList<>();
        String sql = "SELECT * FROM ticket";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("Ticket_id");
                String text = resultSet.getString("Ticket_text");
                int saleId = resultSet.getInt("Ticket_saleId");
                /*/Ticket ticket = new Ticket(text,saleId); //Descomentar cuando esté la clase Ticket
                ticket.setId(id);
                ticketList.add(ticket);*/
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch Tickets: ", e);
        }
        return ticketList;
    }

    public Ticket getTicketById(int id) {
        Ticket ticket = null;
        String sql = "SELECT * FROM ticket WHERE Ticket_id = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idT = resultSet.getInt("Ticket_id");
                    int saleId = resultSet.getInt("Ticket_saleId");
                    String text = resultSet.getString("Ticket_text");
                    /*ticket = new Ticket(saleId, text); //Descomentar cuando esté la clase Ticket
                    ticket.setId(idT);*/
                }
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch Ticket by ID: ", e);
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