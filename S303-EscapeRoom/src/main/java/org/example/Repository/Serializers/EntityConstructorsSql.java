package org.example.Repository.Serializers;

import org.example.Modules.Entities.CommunicatesEntities.Certificate;
import org.example.Modules.Entities.CommunicatesEntities.Gift;
import org.example.Modules.Entities.CommunicatesEntities.Notification;
import org.example.Modules.Entities.CommunicatesEntities.Ticket;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;
import org.example.Modules.Entities.GameEntities.Game;
import org.example.Modules.Entities.GameEntities.GameHasPlayer;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Modules.Entities.GameEntities.Sale;
import org.example.Modules.Entities.RoomEntities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class EntityConstructorsSql {



    public static Gift giftConstructor(Map<String, Object> entityData, ArrayList<String> attributes) throws SQLException {
        Gift entity = null;
        int id = (Integer) entityData.get(attributes.get(0));
        int gameId = (Integer) entityData.get(attributes.get(1));
        String text = (String) entityData.get(attributes.get(2));
        String giftKey = (String) entityData.get(attributes.get(3));
        Timestamp createdAt = (Timestamp) entityData.get(attributes.get(4));
        int playerId = (Integer) entityData.get(attributes.get(5));
        entity = new Gift(id, gameId, text, playerId, giftKey, createdAt);
        return entity;
    }

    public static Ticket ticketConstructor(Map<String, Object> entityData, ArrayList<String> attributes) throws SQLException {
        Ticket entity = null;
        int id = (Integer) entityData.get(attributes.get(0));
        int saleId = (Integer) entityData.get(attributes.get(1));
        String text = (String) entityData.get(attributes.get(2));
        int playerId = (Integer) entityData.get(attributes.get(3));
        Timestamp date = (Timestamp) entityData.get(attributes.get(4));
        entity = new Ticket(id, playerId, saleId, text, date);
        return entity;
    }

    public static Certificate certificateConstructor(Map<String, Object> entityData, ArrayList<String> attributes) throws SQLException {
        Certificate entity = null;
        int id = (Integer) entityData.get(attributes.get(0));
        int gameId = (Integer) entityData.get(attributes.get(1));
        String text = (String) entityData.get(attributes.get(2));
        Timestamp created_at = (Timestamp) entityData.get(attributes.get(3));
        int playerId = (Integer) entityData.get(attributes.get(4));
        entity = new Certificate(id, gameId, playerId, text, created_at);
        return entity;
    }

    public static Notification notificationConstructor(Map<String, Object> entityData, ArrayList<String> attributes) throws SQLException {
        Notification entity = null;
        int id = (Integer) entityData.get(attributes.get(0));
        int playerId = (Integer) entityData.get(attributes.get(1));
        String text = (String) entityData.get(attributes.get(2));
        Timestamp created_at = (Timestamp) entityData.get(attributes.get(3));
        entity = new Notification(id, playerId, text, created_at);
        return entity;
    }

    public static Game gameConstructor(Map<String, Object> entityData, ArrayList<String> attributes) throws SQLException {
        Game entity = null;
        int id = (Integer) entityData.get(attributes.get(0));
        int escapeRoomId = (Integer) entityData.get(attributes.get(1));
        int finished = (Integer) entityData.get(attributes.get(2));
        int deleted = (Integer) entityData.get(attributes.get(3));
        Timestamp createdAt = (Timestamp) entityData.get(attributes.get(4));
        Timestamp updateAt = (Timestamp) entityData.get(attributes.get(5));
        entity = new Game(id, escapeRoomId, finished, deleted, createdAt, updateAt);
        return entity;
    }

    public static Room roomConstructor(Map<String, Object> entityData, ArrayList<String> attributes) throws SQLException {
        Room entity = null;
        int id = (Integer) entityData.get(attributes.get(0));
        String name = (String) entityData.get(attributes.get(1));
        String difficulty = (String) entityData.get(attributes.get(2));
        double price = (Double) entityData.get(attributes.get(3));
        int deleted = (Integer) entityData.get(attributes.get(4));
        Timestamp createdAt = (Timestamp) entityData.get(attributes.get(5));
        Timestamp updateAt = (Timestamp) entityData.get(attributes.get(6));
        entity = new Room(id, name, difficulty, price, deleted, createdAt, updateAt);
        return entity;
    }

    public static Sale saleConstructor(Map<String, Object> entityData, ArrayList<String> attributes) throws SQLException {
        Sale entity = null;
        int id = (Integer) entityData.get(attributes.get(0));
        double price = (Double) entityData.get(attributes.get(1));
        int gameId = (Integer) entityData.get(attributes.get(2));
        int deleted = (Integer) entityData.get(attributes.get(3));
        Timestamp createdAt = (Timestamp) entityData.get(attributes.get(4));
        entity = new Sale(id, price, gameId, deleted, createdAt);
        return entity;
    }

    public static Tips tipsConstructor(Map<String, Object> entityData, ArrayList<String> attributes) throws SQLException {
        Tips entity = null;
        int id = (Integer) entityData.get(attributes.get(0));
        String text = (String) entityData.get(attributes.get(1));
        int deleted = (Integer) entityData.get(attributes.get(2));
        entity = new Tips(id, text, deleted);
        return entity;
    }

    public static Player playerConstructor(Map<String, Object> entityData, ArrayList<String> attributes) throws SQLException {
        Player entity = null;
        int id = (Integer) entityData.get(attributes.get(0));
        String name = (String) entityData.get(attributes.get(1));
        String email = (String) entityData.get(attributes.get(2));
        int consetNotif = (Integer) entityData.get(attributes.get(3));
        int deleted = (Integer) entityData.get(attributes.get(4));
        Timestamp createdAt = (Timestamp) entityData.get(attributes.get(5));
        Timestamp updateAt = (Timestamp) entityData.get(attributes.get(6));
        entity = new Player(id, name, email, consetNotif, deleted, createdAt, updateAt);
        return entity;
    }

    public static EscapeRoom escapeRoomConstructor(Map<String, Object> entityData, ArrayList<String> attributes) throws SQLException {
        EscapeRoom entity = null;
        int id = (Integer) entityData.get(attributes.get(0));
        String name = (String) entityData.get(attributes.get(1));
        double price = (Double) entityData.get(attributes.get(2));
        String theme = (String) entityData.get(attributes.get(3));
        int deleted = (Integer) entityData.get(attributes.get(4));
        Timestamp createdAt = (Timestamp) entityData.get(attributes.get(5));
        Timestamp updateAt = (Timestamp) entityData.get(attributes.get(6));
        entity = new EscapeRoom(id, name, price, theme, deleted, createdAt, updateAt);
        return entity;
    }

    public static ObjectDeco objectDecoConstructor(Map<String, Object> entityData, ArrayList<String> attributes) throws SQLException {
        ObjectDeco entity = null;
        int id = (Integer) entityData.get(attributes.get(0));
        String name = (String) entityData.get(attributes.get(1));
        String material = (String) entityData.get(attributes.get(2));
        double price = (Double) entityData.get(attributes.get(3));
        int deleted = (Integer) entityData.get(attributes.get(4));
        Timestamp createdAt = (Timestamp) entityData.get(attributes.get(5));
        Timestamp updateAt = (Timestamp) entityData.get(attributes.get(6));
        entity = new ObjectDeco(id, name, material, price, deleted, createdAt, updateAt);
        return entity;
    }

    public static RoomHasObject roomHasObjectConstructor(Map<String, Object> entityData, ArrayList<String> attributes) throws SQLException {
        RoomHasObject entity = null;
        int idObject = (Integer) entityData.get(attributes.get(0));
        int idRoom = (Integer) entityData.get(attributes.get(1));
        entity = new RoomHasObject(idObject, idRoom);
        return entity;
    }

    public static RoomHasTips roomHasTipsConstructor(Map<String, Object> entityData, ArrayList<String> attributes) throws SQLException {
        RoomHasTips entity = null;
        int idTip = (Integer) entityData.get(attributes.get(0));
        int idRoom = (Integer) entityData.get(attributes.get(1));
        entity = new RoomHasTips(idTip, idRoom);
        return entity;
    }

    public static GameHasPlayer gameHasPlayerConstructor(Map<String, Object> entityData, ArrayList<String> attributes) throws SQLException {
        GameHasPlayer entity = null;
        int idPlayer = (Integer) entityData.get(attributes.get(0));
        int idRoom = (Integer) entityData.get(attributes.get(1));
        entity = new GameHasPlayer(idPlayer, idRoom);
        return entity;
    }
}

