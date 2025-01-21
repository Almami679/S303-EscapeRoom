package org.example.Repository.Serializers;

import org.example.Modules.Entities.CommunicatesEntities.Certificate;
import org.example.Modules.Entities.CommunicatesEntities.Gift;
import org.example.Modules.Entities.CommunicatesEntities.Notification;
import org.example.Modules.Entities.CommunicatesEntities.Ticket;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;
import org.example.Modules.Entities.GameEntities.Game;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Modules.Entities.GameEntities.Sale;
import org.example.Modules.Entities.RoomEntities.ObjectDeco;
import org.example.Modules.Entities.RoomEntities.Room;
import org.example.Modules.Entities.RoomEntities.Tips;

import java.sql.*;
import java.util.ArrayList;

public class EntityConstructorsSql {

    public static Gift giftConstructor(ResultSet resultSet, ArrayList<String> attributes) throws SQLException {
        Gift entity = null;
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

    public static Ticket ticketConstructor(ResultSet resultSet, ArrayList<String> attributes) throws SQLException {
        Ticket entity = null;
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

    public static Certificate certificateConstructor(ResultSet resultSet, ArrayList<String> attributes) throws SQLException {
        Certificate entity = null;
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

    public static Notification notificationConstructor(ResultSet resultSet, ArrayList<String> attributes) throws SQLException {
        Notification entity = null;
        if (resultSet.next()) {
            int id = resultSet.getInt(attributes.get(0));
            int playerId = resultSet.getInt(attributes.get(1));
            String text = resultSet.getString(attributes.get(2));
            Timestamp created_at = resultSet.getTimestamp((attributes.get(3)));
            entity = new Notification(id, playerId, text, created_at);
        }
        return entity;
    }

    public static Game gameConstructor(ResultSet resultSet, ArrayList<String> attributes) throws SQLException {
        Game entity = null;
        if (resultSet.next()) {
            int id = resultSet.getInt(attributes.get(0));
            int escapeRoomId = resultSet.getInt(attributes.get(1));
            int finished = resultSet.getInt(attributes.get(2));
            int deleted = resultSet.getInt(attributes.get(3));
            Timestamp createdAt = resultSet.getTimestamp((attributes.get(4)));
            Timestamp updateAt = resultSet.getTimestamp(attributes.get(5));
            entity = new Game(id,escapeRoomId, finished, deleted, createdAt, updateAt);
        }
        return entity;
    }

    public static Room roomConstructor(ResultSet resultSet, ArrayList<String> attributes) throws SQLException {
        Room entity = null;
        if (resultSet.next()) {
            int id = resultSet.getInt(attributes.get(0));
            String name = resultSet.getString((attributes.get(1)));
            String difficulty = resultSet.getString((attributes.get(2)));
            double price = resultSet.getDouble(attributes.get(3));
            int deleted = resultSet.getInt(attributes.get(4));
            Timestamp createdAt = resultSet.getTimestamp((attributes.get(5)));
            Timestamp updateAt = resultSet.getTimestamp(attributes.get(6));
            entity = new Room(id,name,difficulty,price,deleted,createdAt,updateAt);
        }
        return entity;
    }

    public static Sale saleConstructor(ResultSet resultSet, ArrayList<String> attributes) throws SQLException {
        Sale entity = null;
        if (resultSet.next()) {
            int id = resultSet.getInt(attributes.get(0));
            double price = resultSet.getDouble(attributes.get(1));
            int gameId = resultSet.getInt(attributes.get(2));
            int deleted = resultSet.getInt(attributes.get(4));
            entity = new Sale(id,price,gameId,deleted);
        }
        return entity;
    }

    public static Tips tipsConstructor(ResultSet resultSet, ArrayList<String> attributes) throws SQLException {
        Tips entity = null;
        if (resultSet.next()) {
            int id = resultSet.getInt(attributes.get(0));
            String text = resultSet.getString(attributes.get(1));
            int deleted = resultSet.getInt(attributes.get(2));
            entity = new Tips(id,text,deleted);
        }
        return entity;
    }

    public static Player playerConstructor(ResultSet resultSet, ArrayList<String> attributes) throws SQLException {
        Player entity = null;
        if (resultSet.next()) {
            int id = resultSet.getInt(attributes.get(0));
            String name = resultSet.getString(attributes.get(1));
            String email = resultSet.getString(attributes.get(2));
            int consetNotif = resultSet.getInt(attributes.get(3));
            int deleted = resultSet.getInt(attributes.get(4));
            Timestamp createdAt = resultSet.getTimestamp((attributes.get(5)));
            Timestamp updateAt = resultSet.getTimestamp(attributes.get(6));
            entity = new Player(id,name,email,consetNotif,deleted, createdAt,updateAt);
        }
        return entity;
    }

    public static EscapeRoom escapeRoomConstructor(ResultSet resultSet, ArrayList<String> attributes) throws SQLException {
        EscapeRoom entity = null;
        if (resultSet.next()) {
            int id = resultSet.getInt(attributes.get(0));
            String name = resultSet.getString(attributes.get(1));
            double price = resultSet.getDouble(attributes.get(2));
            String theme = resultSet.getString(attributes.get(3));
            int deleted = resultSet.getInt(attributes.get(4));
            Timestamp createdAt = resultSet.getTimestamp((attributes.get(5)));
            Timestamp updateAt = resultSet.getTimestamp(attributes.get(6));
            entity = new EscapeRoom(id,name,price,theme,deleted, createdAt,updateAt);
        }
        return entity;
    }

    public static ObjectDeco objectDecoConstructor(ResultSet resultSet, ArrayList<String> attributes) throws SQLException {
        ObjectDeco entity = null;
        if (resultSet.next()) {
            int id = resultSet.getInt(attributes.get(0));
            String name = resultSet.getString(attributes.get(1));
            String material = resultSet.getString(attributes.get(3));
            double price = resultSet.getDouble(attributes.get(2));
            int deleted = resultSet.getInt(attributes.get(4));
            Timestamp createdAt = resultSet.getTimestamp((attributes.get(5)));
            Timestamp updateAt = resultSet.getTimestamp(attributes.get(6));
            entity = new ObjectDeco(id,name,material,price,deleted, createdAt,updateAt);
        }
        return entity;
    }
}
