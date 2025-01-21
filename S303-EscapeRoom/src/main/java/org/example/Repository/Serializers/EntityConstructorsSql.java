package org.example.Repository.Serializers;

import org.example.Modules.Entities.CommunicatesEntities.Certificate;
import org.example.Modules.Entities.CommunicatesEntities.Gift;
import org.example.Modules.Entities.CommunicatesEntities.Notification;
import org.example.Modules.Entities.CommunicatesEntities.Ticket;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;

import java.sql.*;
import java.util.ArrayList;

public class EntityConstructorsSql {

    public static Entity giftConstructor(ResultSet resultSet, ArrayList<String> attributes) throws SQLException {
        Entity entity = null;
        if (resultSet.next()) {
            int id = resultSet.getInt(attributes.get(0));
            int gameId = resultSet.getInt(attributes.get(1));
            String text = resultSet.getString(attributes.get(2));
            String giftKey = resultSet.getString(attributes.get(3));
            int playerId = resultSet.getInt(attributes.get(4));
            entity = new Gift(id, gameId, text, playerId, giftKey);
        }
        return entity;
    }

    public static Entity ticketConstructor(ResultSet resultSet, ArrayList<String> attributes) throws SQLException {
        Entity entity = null;
        if (resultSet.next()) {
            int id = resultSet.getInt(attributes.get(0));
            int saleId = resultSet.getInt(attributes.get(1));
            String text = resultSet.getString(attributes.get(2));
            int playerId = resultSet.getInt(attributes.get(3));
            Timestamp date = resultSet.getTimestamp(attributes.get(4));
            entity = new Ticket(id, playerId, saleId, text, date);
        }
        return entity;
    }

    public static Entity certificateConstructor(ResultSet resultSet, ArrayList<String> attributes) throws SQLException {
        Entity entity = null;
        if (resultSet.next()) {
            int id = resultSet.getInt(attributes.get(0));
            int gameId = resultSet.getInt(attributes.get(1));
            String text = resultSet.getString(attributes.get(2));
            Timestamp created_at = resultSet.getTimestamp(attributes.get(3));
            int playerId = resultSet.getInt(attributes.get(4));
            entity = new Certificate(id, gameId, playerId, text, created_at);
        }
        return entity;
    }

    public static Entity notificationConstructor(ResultSet resultSet, ArrayList<String> attributes) throws SQLException {
        Entity entity = null;
        if (resultSet.next()) {
            int id = resultSet.getInt(attributes.get(0));
            int playerId = resultSet.getInt(attributes.get(1));
            String text = resultSet.getString(attributes.get(2));
            Timestamp created_at = resultSet.getTimestamp((attributes.get(3)));
            entity = new Notification(id, playerId, text, created_at);
        }
        return entity;
    }


    public static Entity escapeRoomConstructor(ResultSet resultSet, ArrayList<String> attributes) throws SQLException {
        Entity entity = null;
        if (resultSet.next()) {
            int id = resultSet.getInt(attributes.get(0));
            String name = resultSet.getString(attributes.get(1));
            Double price = resultSet.getDouble(attributes.get(2));
            String theme = resultSet.getString(attributes.get(3));
            int deleted = resultSet.getInt(attributes.get(4));
            Timestamp createdAt = resultSet.getTimestamp(attributes.get(5));
            Timestamp updatedAt = resultSet.getTimestamp(attributes.get(6));
            entity = new EscapeRoom(id, name, price, theme, deleted, createdAt, updatedAt);
        }
        return entity;
    }

}
