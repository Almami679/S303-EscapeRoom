package org.example.Repository.Old;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Entities.RoomEntities.ObjectDeco;
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

    public void addObjectDeco(ObjectDeco objectDeco) {
        String sql = "INSERT INTO objectdeco (ObjectDeco_id, ObjectDeco_name, ObjectDeco_material, ObjectDeco_price, ObjectDeco_deleted, ObjectDeco_createdAt, ObjectDeco_updatedAt)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, objectDeco.getId());
            statement.setString(2, objectDeco.getName());
            statement.setString(3, objectDeco.getMaterial());
            statement.setDouble(4, objectDeco.getPrice());
            statement.setInt(5, objectDeco.getDeleted());
            statement.setTimestamp(6, objectDeco.getCreatedAt());
            statement.setTimestamp(7, objectDeco.getUpdatedAt());
            statement.executeUpdate();
            logger.info("ObjectDeco added.");
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to add ObjectDeco: ", e);
        }
    }

    public ObjectDeco getObjectDecoById(int id) {
        ObjectDeco objectDecoTEST = null;
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
                    objectDecoTEST = new ObjectDeco(name, material, price);
                    objectDecoTEST.setId(id);
                }
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch ObjectDeco by ID: ", e);
        }
        return objectDecoTEST;
    }

    public ArrayList<ObjectDeco> getAllObjectDecos() {
        ArrayList<ObjectDeco> objectDecoTESTList = new ArrayList<>();
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

                ObjectDeco objectDecoTEST = new ObjectDeco(name, material, price);
                objectDecoTEST.setId(id);
                objectDecoTESTList.add(objectDecoTEST);
            }
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to fetch ObjectDeco: ", e);
        }
        return objectDecoTESTList;
    }

    public void updateObjectDeco(ObjectDeco objectDeco) {
        String sql = "UPDATE objectdeco SET ObjectDeco_name = ?, ObjectDeco_material = ?, ObjectDeco_roomId = ?, ObjectDeco_price = ?, ObjectDeco_deleted = ?, ObjectDeco_updatedAt = ? WHERE ObjectDeco_id = ?";
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, objectDeco.getName());
            statement.setString(2, objectDeco.getMaterial());
            statement.setDouble(3, objectDeco.getPrice());
            statement.setInt(4, objectDeco.getDeleted());
            statement.setTimestamp(5, objectDeco.getUpdatedAt());
            statement.setInt(6, objectDeco.getId());
            statement.executeUpdate();
            logger.info("ObjectDeco updated.");
            dbConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Failed to update ObjectDeco: ", e);
        }
    }
}