package org.example.Repository.RepositoryRelations;

import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;
import org.example.Repository.Serializers.Serializer;

import java.sql.SQLException;
import java.util.ArrayList;

public class RepositoryRoomHasTips extends RepositoryImpl {
    public RepositoryRoomHasTips() {
        super();
    }

    public void addRoomHasTips(int tipsId, int roomId) throws SQLException {
        String query = "INSERT INTO escaperoomdb.room_has_tips (tips_tips_id, room_room_id) VALUES (?, ?);";
        ArrayList<String> values = new ArrayList<>();
        values.add(String.valueOf(tipsId));
        values.add(String.valueOf(roomId));
        Serializer.serialize(query, EntityAttributes.room_has_tips, "add", values);
    }
}