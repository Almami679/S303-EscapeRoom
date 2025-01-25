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

    public void addRoomHasTips(int tipsId, int roomId) throws SQLException {
        String query = "INSERT INTO escaperoomdb.room_has_tips (tips_tips_id, room_room_id) VALUES (?, ?);";
        ArrayList<String> values = new ArrayList<>();
        values.add(String.valueOf(tipsId));
        values.add(String.valueOf(roomId));
        Serializer.serialize(query, EntityAttributes.room_has_tips, "add", values);
    }

    public ArrayList<Tips> getAllTipsByRoomId(int roomId) throws SQLException {
        String query = "SELECT * FROM escaperoomdb.room_has_tips WHERE room_room_id = " + roomId +";";
        ArrayList<Entity> IdTipsInRoom = new ArrayList<>();
        ArrayList<Tips> tipsInRoom = new ArrayList<>();
        IdTipsInRoom = Serializer.deserializeGetAll(query,EntityAttributes.room_has_tips);
        IdTipsInRoom.forEach(entityForId -> {
            try {
                RoomHasTips entity = (RoomHasTips) entityForId;
                tipsInRoom.add((Tips) getById(entity.getIdTip(), EntityAttributes.tips));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return tipsInRoom;
    }
}