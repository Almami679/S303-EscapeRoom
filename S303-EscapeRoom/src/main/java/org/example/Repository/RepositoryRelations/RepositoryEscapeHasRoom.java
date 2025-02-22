// RepositoryEscapeHasRoom.java
package org.example.Repository.RepositoryRelations;

import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoomHasRoom;
import org.example.Modules.Entities.RoomEntities.Room;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;
import org.example.Repository.Serializers.Serializer;

import java.sql.SQLException;
import java.util.ArrayList;

public class RepositoryEscapeHasRoom extends RepositoryImpl {
    public RepositoryEscapeHasRoom() {
        super();
    }

    public void addEscapeRoomHasRoom(Entity entity) throws SQLException {
        String query = "INSERT INTO escaperoomdb.escaperoom_has_room (escaperoom_escaperoom_id, room_room_id) VALUES (?, ?);";
        ArrayList<String> values = entity.getValues();
        Serializer.serialize(query, EntityAttributes.escaperoom_has_room, "add", values);
    }

    public ArrayList<Room> getAllRoomsByEscapeRoomId(int escapeRoomId) throws SQLException {
        String query = "SELECT * FROM escaperoomdb.escaperoom_has_room ehr " +
                "INNER JOIN escaperoomdb.room r ON ehr.room_room_id = r.room_id " +
                "WHERE ehr.escaperoom_escaperoom_id = " + escapeRoomId + " AND r.room_deleted = 0;";
        ArrayList<Entity> IdRoomsInEscapeRoom;
        ArrayList<Room> roomList = new ArrayList<>();
        IdRoomsInEscapeRoom = Serializer.deserializeGetAll(query,EntityAttributes.escaperoom_has_room);
        IdRoomsInEscapeRoom.forEach(entityForId -> {
            try {
                EscapeRoomHasRoom entity = (EscapeRoomHasRoom) entityForId;
                roomList.add((Room) getById(entity.getIdRoom(), EntityAttributes.room));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return roomList;
    }
}