package org.example.Repository.RepositoryRelations;

import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.RoomEntities.ObjectDeco;
import org.example.Modules.Entities.RoomEntities.RoomHasObject;
import org.example.Modules.Entities.RoomEntities.RoomHasTips;
import org.example.Modules.Entities.RoomEntities.Tips;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;
import org.example.Repository.Serializers.Serializer;

import java.sql.SQLException;
import java.util.ArrayList;

public class RepositoryRoomHasTips extends RepositoryImpl {
    public RepositoryRoomHasTips() {
        super();
    }

    public ArrayList<Tips> getAllTipsByRoomId(int roomId) throws SQLException {
        String query = "SELECT t.* FROM escaperoomdb.room_has_tips rht " +
                "INNER JOIN escaperoomdb.tips t ON rht.tips_tips_id = t.tips_id " +
                "WHERE rht.room_room_id = " + roomId + " AND t.tips_deleted = 0;";
        ArrayList<Entity> tipsEntities = Serializer.deserializeGetAll(query, EntityAttributes.tips);
        ArrayList<Tips> tipsList = new ArrayList<>();
        for (Entity entity : tipsEntities) {
            tipsList.add((Tips) entity);
        }
        return tipsList;
    }

    public void addTipToRoom(Entity entity) throws SQLException {
        String query = "INSERT INTO escaperoomdb.room_has_tips (tips_tips_id, room_room_id) VALUES (?, ?);";
        ArrayList<String> values = entity.getValues();
        Serializer.serialize(query, EntityAttributes.room_has_tips, "add", values);
    }
}