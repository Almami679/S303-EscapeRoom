// RepositoryEscapeHasRoom.java
package org.example.Repository.Common;

import org.example.Repository.Serializers.Serializer;

import java.sql.SQLException;
import java.util.ArrayList;

public class RepositoryEscapeHasRoom extends RepositoryImpl {
    public RepositoryEscapeHasRoom() {
        super();
    }

    public void addEscapeRoomHasRoom(int escapeRoomId, int roomId) throws SQLException {
        String query = "INSERT INTO escaperoomdb.escaperoom_has_room (escaperoom_escaperoom_id, room_room_id) VALUES (?, ?);";
        ArrayList<String> values = new ArrayList<>();
        values.add(String.valueOf(escapeRoomId));
        values.add(String.valueOf(roomId));
        Serializer.serialize(query, EntityAttributes.escaperoom_has_room, "add", values);
    }
}