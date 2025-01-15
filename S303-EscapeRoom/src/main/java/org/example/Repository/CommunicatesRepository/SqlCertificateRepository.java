package org.example.Repository.CommunicatesRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Communicates.Certificate;
import org.example.Repository.Common.DatabaseConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlCertificateRepository {
    private static final Logger logger = LogManager.getLogger(SqlTicketRepository.class);
    private static DatabaseConnection dbConnection;

    public SqlCertificateRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addCertificate(Certificate certificate) {
        String sql = "INSERT INTO certificate (" +
                "Certificate_id, " +
                "Certificate_gameId, " +
                "Certificate_text, " +
                "Certificate_playerId) " +
                "VALUES (?, ?, ?, ?)";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, certificate.getId());
            statement.setInt(2, certificate.getGame().getId());
            statement.setString(3, certificate.getText());
            statement.setInt(4, certificate.getPlayer().getId());
            statement.executeUpdate();
            logger.info("Certificate added. [Id: " + certificate.getId() + "]");
        } catch (SQLException e) {
            logger.error("Failed to add Certificate: ", e);
        }
    }

    public static Certificate getCertificateById(int id) {
        Certificate certificate = null;
        String sql = "SELECT * FROM certificate WHERE Certificate_id = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int gameId = resultSet.getInt("Certificate_gameId");
                    String text = resultSet.getString("Certificate_text");
                    int playerId = resultSet.getInt("Certificate_playerId");
                    certificate = new Certificate(id, gameId, playerId, text);
                }
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch Certificate by ID: " + id, e);
        }
        return certificate;
    }


}
