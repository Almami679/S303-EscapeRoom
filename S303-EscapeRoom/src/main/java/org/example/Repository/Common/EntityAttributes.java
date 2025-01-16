package org.example.Repository.Common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum EntityAttributes {
    CERTIFICATE(
            "certificate_id",
            "certificate_gameId",
            "certificate_text",
            "certificate_createdAt",
            "player_player_id"
    ),
    ESCAPEROOM(
            "escaperoom_id",
            "escaperoom_name",
            "escaperoom_price",
            "escaperoom_theme",
            "escaperoom_deleted",
            "escaperoom_createdAt",
            "escaperoom_updatedAt"
    ),
    GAME(
            "game_id",
            "game_escapeRoomId",
            "game_finished",
            "game_deleted",
            "game_createdAt",
            "game_updatedAt"
    ),
    GAMEHASPLAYER(
            "game_game_id",
            "player_player_id"
    ),
    GIFT(
            "gift_id",
            "gift_gameId",
            "gift_text",
            "gift_key",
            "player_player_id"
    ),
    NOTIFICATION(
            "notification_id",
            "notification_playerId",
            "notification_text",
            "notification_createdAt"
    ),
    OBJECTDECO(
            "objectdeco_id",
            "objectdeco_name",
            "objectdeco_material",
            "objectdeco_price",
            "objectdeco_deleted",
            "objectdeco_createdAt",
            "objectdeco_updatedAt"
    ),
    PLAYER(
            "player_id",
            "player_name",
            "player_email",
            "player_consentNotif",
            "player_deleted",
            "player_createdAt",
            "player_updatedAt"
    ),
    ROOM(
            "room_id",
            "room_name",
            "room_difficulty",
            "room_price",
            "room_escapeRoomId",
            "room_deleted",
            "room_createdAt",
            "room_updatedAt"
    ),
    ROOMHASOBJECTDECO(
            "room_room_id",
            "objectdeco_objectdeco_id"
    ),
    ROOMHASTIPS(
            "room_room_id",
            "tips_tips_id"
    ),
    SALE(
            "sale_id",
            "sale_price",
            "sale_gameId",
            "sale_deleted"
    ),
    TICKET(
            "ticket_id",
            "ticket_saleId",
            "ticket_text",
            "ticket_playerId",
            "ticket_createdAt"
    ),
    TIPS(
            "tips_id",
            "tips_text"
    );

    private final List<String> attributes;

    EntityAttributes(String... attributes) {
        this.attributes = new ArrayList<>(Arrays.asList(attributes));
    }

    public List<String> getAttributes() {
        return attributes;
    }
}