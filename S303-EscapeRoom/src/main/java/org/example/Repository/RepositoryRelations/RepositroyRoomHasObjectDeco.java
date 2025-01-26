package org.example.Repository.RepositoryRelations;

import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.RoomEntities.ObjectDeco;
import org.example.Modules.Entities.RoomEntities.RoomHasObject;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;
import org.example.Repository.Serializers.Serializer;

import java.sql.SQLException;
import java.util.ArrayList;

public class RepositroyRoomHasObjectDeco extends RepositoryImpl {
    public RepositroyRoomHasObjectDeco() {
        super();
    }

    public void addRoomHasObjectDeco(int roomId, int objectdecoId) throws SQLException {
        String query = "INSERT INTO escaperoomdb.room_has_objectdeco (room_room_id, objectdeco_objectdeco_id) VALUES (?, ?);";
        ArrayList<String> values = new ArrayList<>();
        values.add(String.valueOf(roomId));
        values.add(String.valueOf(objectdecoId));
        Serializer.serialize(query, EntityAttributes.room_has_objectdeco, "add", values);
    }

    public ArrayList<ObjectDeco> getAllObjectsByRoomId(int roomId) throws SQLException {
        String query = "SELECT * FROM escaperoomdb.room_has_objectdeco WHERE room_room_id = " + roomId +";";
        ArrayList<Entity> IdObjectsInRoom;
        ArrayList<ObjectDeco> objectsInRoom = new ArrayList<>();
        IdObjectsInRoom = Serializer.deserializeGetAll(query,EntityAttributes.room_has_objectdeco);
        IdObjectsInRoom.forEach(entityForId -> {
            try {
                RoomHasObject entity = (RoomHasObject) entityForId;
                objectsInRoom.add((ObjectDeco) getById(entity.getIdObject(), EntityAttributes.objectdeco));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return objectsInRoom;
    }

}