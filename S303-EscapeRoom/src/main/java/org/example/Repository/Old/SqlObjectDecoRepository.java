package org.example.Repository.Old;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Entities.CLASESTESTS.ObjectDecoTEST;
import org.example.Repository.Common.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class SqlObjectDecoRepository {
    private static final Logger logger = LogManager.getLogger(SqlObjectDecoRepository.class);
    private final DatabaseConnection dbConnection;

    public SqlObjectDecoRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public boolean isDuplicateObjectDeco(String objectDecoName) {
        String sql = "SELECT COUNT(*) FROM objectdeco WHERE ObjectDeco_name = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, objectDecoName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to check for duplicate ObjectDeco: ", e);
        }
        return false;
    }

    public void addObjectDeco(ObjectDecoTEST objectDecoTEST) {
        String sql = "INSERT INTO objectdeco (ObjectDeco_id, ObjectDeco_name, ObjectDeco_material, ObjectDeco_roomId, ObjectDeco_price, ObjectDeco_deleted, ObjectDeco_createdAt, ObjectDeco_updatedAt)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, objectDecoTEST.getId());
            statement.setString(2, objectDecoTEST.getName());
            statement.setString(3, objectDecoTEST.getMaterial());
            statement.setInt(4, objectDecoTEST.getRoomId());
            statement.setDouble(5, objectDecoTEST.getPrice());
            statement.setInt(6, objectDecoTEST.getDeleted());
            statement.setTimestamp(7, objectDecoTEST.getCreatedAt());
            statement.setTimestamp(8, objectDecoTEST.getUpdatedAt());
            statement.executeUpdate();
            logger.info("ObjectDeco added.");
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to add ObjectDeco: ", e);
        }
    }

    public ObjectDecoTEST getObjectDecoById(int id) {
        ObjectDecoTEST objectDecoTEST = null;
        String sql = "SELECT * FROM objectdeco WHERE ObjectDeco_id = ? AND ObjectDeco_deleted = 0";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("ObjectDeco_name");
                    String material = resultSet.getString("ObjectDeco_material");
                    int roomId = resultSet.getInt("ObjectDeco_roomId");
                    double price = resultSet.getDouble("ObjectDeco_price");
                    int deleted = resultSet.getInt("ObjectDeco_deleted");
                    Timestamp createdAt = resultSet.getTimestamp("ObjectDeco_createdAt");
                    Timestamp updatedAt = resultSet.getTimestamp("ObjectDeco_updatedAt");
                    objectDecoTEST = new ObjectDecoTEST(name, material, roomId, price, deleted, createdAt, updatedAt);
                    objectDecoTEST.setId(id);
                }
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch ObjectDeco by ID: ", e);
        }
        return objectDecoTEST;
    }

    public ArrayList<ObjectDecoTEST> getAllObjectDecos() {
        ArrayList<ObjectDecoTEST> objectDecoTESTList = new ArrayList<>();
        String sql = "SELECT * FROM objectdeco WHERE ObjectDeco_deleted = 0 ORDER BY ObjectDeco_id DESC";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("ObjectDeco_id");
                String name = resultSet.getString("ObjectDeco_name");
                String material = resultSet.getString("ObjectDeco_material");
                int roomId = resultSet.getInt("ObjectDeco_roomId");
                double price = resultSet.getDouble("ObjectDeco_price");
                int deleted = resultSet.getInt("ObjectDeco_deleted");
                Timestamp createdAt = resultSet.getTimestamp("ObjectDeco_createdAt");
                Timestamp updatedAt = resultSet.getTimestamp("ObjectDeco_updatedAt");

                ObjectDecoTEST objectDecoTEST = new ObjectDecoTEST(name, material, roomId, price, deleted, createdAt, updatedAt);
                objectDecoTEST.setId(id);
                objectDecoTESTList.add(objectDecoTEST);
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch ObjectDeco: ", e);
        }
        return objectDecoTESTList;
    }

    public void updateObjectDeco(ObjectDecoTEST objectDecoTEST) {
        String sql = "UPDATE objectdeco SET ObjectDeco_name = ?, ObjectDeco_material = ?, ObjectDeco_roomId = ?, ObjectDeco_price = ?, ObjectDeco_deleted = ?, ObjectDeco_updatedAt = ? WHERE ObjectDeco_id = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, objectDecoTEST.getName());
            statement.setString(2, objectDecoTEST.getMaterial());
            statement.setInt(3, objectDecoTEST.getRoomId());
            statement.setDouble(4, objectDecoTEST.getPrice());
            statement.setInt(5, objectDecoTEST.getDeleted());
            statement.setTimestamp(6, objectDecoTEST.getUpdatedAt());
            statement.setInt(7, objectDecoTEST.getId());
            statement.executeUpdate();
            logger.info("ObjectDeco updated.");
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to update ObjectDeco: ", e);
        }
    }
}