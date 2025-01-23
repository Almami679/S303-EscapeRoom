package org.example.Repository.RepositoryRelations;

import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;
import org.example.Repository.Serializers.Serializer;

import java.sql.SQLException;
import java.util.ArrayList;

public class RepositoryRoomHasObjectDeco extends RepositoryImpl {
    public RepositoryRoomHasObjectDeco() {
        super();
    }

    public void addRoomHasObjectDeco(int roomId, int objectdecoId) throws SQLException {
        String query = "INSERT INTO escaperoomdb.room_has_objectdeco (room_room_id, objectdeco_objectdeco_id) VALUES (?, ?);";
        ArrayList<String> values = new ArrayList<>();
        values.add(String.valueOf(roomId));
        values.add(String.valueOf(objectdecoId));
        Serializer.serialize(query, EntityAttributes.room_has_objectdeco, "add", values);
    }
}