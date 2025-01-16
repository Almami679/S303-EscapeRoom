package org.example.Repository.Common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum EntityAttributes {
    CERTIFICATE(
            "Certificate_id",
            "Certificate_gameId",
            "Certificate_text",
            "Certificate_playerId"
    ),
    ESCAPEROOM(
            "EscapeRoom_id",
            "EscapeRoom_name",
            "EscapeRoom_price",
            "EscapeRoom_theme",
            "EscapeRoom_deleted",
            "EscapeRoom_createdAt",
            "EscapeRoom_updatedAt"
    ),
    GAME(
            "Game_id",
            "Game_date",
            "Game_escapeRoomId",
            "Game_finished",
            "Game_deleted"
    ),
    GAMEHASPLAYER(
            "GameHasPlayer_gameId",
            "GameHasPlayer_playerId"
    ),
    GIFT(
            "Gift_id",
            "Gift_gameId",
            "Gift_text",
            "Gift_playerId",
            "Gift_giftKey"
    ),
    NOTIFICATION(
            "Notification_id",
            "Notification_playerId",
            "Notification_text"

    ),
    OBJECTDECO(
            "ObjectDeco_id",
            "ObjectDeco_name",
            "ObjectDeco_material",
            "ObjectDeco_roomId",
            "ObjectDeco_price",
            "ObjectDeco_deleted",
            "ObjectDeco_createdAt",
            "ObjectDeco_updatedAt"
    ),
    PLAYER(
            "Player_id",
            "Player_name",
            "Player_email",
            "Player_consentNotif",
            "Player_deleted"
    ),
    ROOM(
            "Room_id",
            "Room_name",
            "Room_difficulty",
            "Room_price",
            "Room_escapeRoomId",
            "Room_deleted",
            "Room_capacity",
            "Room_createdAt",
            "Room_updatedAt"
    ),
    SALE(
            "Sale_id",
            "Sale_date",
            "Sale_price",
            "Sale_gameId",
            "Sale_deleted"
    ),
    TICKET(
            "Ticket_id",
            "Ticket_saleId",
            "Ticket_text",
            "Ticket_playerId"
    ),
    TIPS(
            "Tips_id",
            "Tips_text",
            "Tips_Room_id"
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