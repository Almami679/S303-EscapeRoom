package org.example.Repository.Old;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Repository.Common.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class SqlTipsRepository {
    private static final Logger logger = LogManager.getLogger(SqlTipsRepository.class);
    private final DatabaseConnection dbConnection;

    public SqlTipsRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public boolean isDuplicateTips(String tipText) {
        String sql = "SELECT COUNT(*) FROM tips WHERE Tips_text = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tipText);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to check for duplicate Tip: ", e);
        }
        return false;
    }
}