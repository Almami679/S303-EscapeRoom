package org.example.Repository.Common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum EntityAttributes {
    certificate(
            "certificate_id",
            "certificate_gameId",
            "certificate_text",
            "certificate_createdAt",
            "player_player_id"
    ),
    escaperoom(
            "escaperoom_id",
            "escaperoom_name",
            "escaperoom_price",
            "escaperoom_theme",
            "escaperoom_deleted",
            "escaperoom_createdAt",
            "escaperoom_updatedAt"
    ),
    game(
            "game_id",
            "game_escapeRoomId",
            "game_finished",
            "game_deleted",
            "game_createdAt",
            "game_updatedAt"
    ),
    game_has_player(
            "game_game_id",
            "player_player_id"
    ),
    gift(
            "gift_id",
            "gift_gameId",
            "gift_text",
            "gift_key",
            "player_player_id"
            //Falta el created_at
    ),
    notification(
            "notification_id",
            "notification_playerId",
            "notification_text",
            "notification_createdAt"
    ),
    objectdeco(
            "objectdeco_id",
            "objectdeco_name",
            "objectdeco_material",
            "objectdeco_price",
            "objectdeco_deleted",
            "objectdeco_createdAt",
            "objectdeco_updatedAt"
    ),
    player(
            "player_id",
            "player_name",
            "player_email",
            "player_consentNotif",
            "player_deleted",
            "player_createdAt",
            "player_updatedAt"
    ),
    room(
            "room_id",
            "room_name",
            "room_difficulty",
            "room_price",
            "room_escapeRoomId",
            "room_deleted",
            "room_createdAt",
            "room_updatedAt"
    ),
    room_has_objectdeco(
            "room_room_id",
            "objectdeco_objectdeco_id"
    ),
    room_has_tips(
            "room_room_id",
            "tips_tips_id"
    ),
    sale(
            "sale_id",
            "sale_price",
            "sale_gameId",
            "sale_deleted"
    ),
    ticket(
            "ticket_id",
            "ticket_saleId",
            "ticket_text",
            "ticket_playerId",
            "ticket_createdAt"
    ),
    tips(
            "tips_id",
            "tips_text",
            "tips_deleted"
    );

    private final ArrayList<String> attributes;

    EntityAttributes(String... attributes) {
        this.attributes = new ArrayList<>();
        Collections.addAll(this.attributes, attributes);
    }

    public ArrayList<String> getAttributes() {
        return attributes;
    }
}