package org.example.Repository.CommunicatesRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Communicates.Notification;
import org.example.Repository.Common.DatabaseConnection;
import org.example.Repository.Old.SqlPlayerRepository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlNotificationRepository {
    static Logger logger = LogManager.getLogger(SqlPlayerRepository.class);
    private static DatabaseConnection dbConnection;

    public SqlNotificationRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addNotification(Notification notification) {
        String sql = "INSERT INTO Notification (Notification_id, Notification_playerId, Notification_text)" +
                " VALUES (?, ?, ?)";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, notification.getId());
            statement.setInt(2, notification.getPlayer().getId());
            statement.setString(3, notification.getText());
            statement.executeUpdate();
            logger.info("Notification created. [Id: " + notification.getId() + "]");
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to create Notification: ", e);
        }
    }

    public static Notification getNotificationById(int id) {
        Notification notification = null;
        String sql = "SELECT * FROM notification WHERE Notification_id = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String text = resultSet.getString("Notification_text");
                    int playerId = resultSet.getInt("Notification_playerId");
                    notification = new Notification(id, playerId, text);
                }
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch Notification by ID: " + id, e);
        }
        return notification;
    }
}
