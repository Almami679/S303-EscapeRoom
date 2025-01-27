package org.example.Services.EscapeRoomServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.PlayerNotFound;
import org.example.Exceptions.RoomNotFoundException;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Modules.Entities.RoomEntities.ObjectDeco;
import org.example.Modules.Entities.RoomEntities.Room;
import org.example.Modules.Entities.RoomEntities.Tips;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;
import org.example.Repository.Common.RepositoryImpl;
import org.example.Repository.RepositoryRelations.RepositoryEscapeHasRoom;
import org.example.Repository.RepositoryRelations.RepositoryRoomHasTips;
import org.example.Repository.RepositoryRelations.RepositroyRoomHasObjectDeco;

import java.sql.SQLException;
import java.util.ArrayList;

public class RoomService {

    private static Logger logger = LogManager.getLogger(RoomService.class);

    private RepositoryImpl repository;
    private final Entity entity = new Entity();


    public RoomService() {
        this.repository = new RepositoryImpl();
    }

    private Room castToRoom(Entity entity) {
        Room room = null;
        if (entity instanceof Room) {
            room = (Room) entity;
        }
        return room;
    }
    private void assertIfRoomIdNotFound(int id) throws SQLException {
        boolean roomFound = this.repository
                .getAll(EntityAttributes.room)
                .stream()
                .map(this::castToRoom)
                .anyMatch(room -> room.getId() == id);

        if (!roomFound) {
            throw new RoomNotFoundException();
        }
    }

    public void createRoom(
            String name,
            double price,
            String dificulty
    ) {
        try {
            this.repository
                    .add(new Room(name, dificulty, price), EntityAttributes.room);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public Room getRoomById(
            int id
    ) {
        try {
            return (Room) this.repository
                    .getById(id, EntityAttributes.room);
        } catch (SQLException e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public void deleteRoom(
            int id
    ){
        try{
            Room room = (Room) this.repository.getById(id, EntityAttributes.room);
            if (room == null) {
                throw new RoomNotFoundException();
            } else {
                this.repository
                        .delete(id, EntityAttributes.room);
            }
        }catch (RoomNotFoundException | SQLException e){
            logger.info(e.getMessage());
        }
    }

    public void updateRoom(
            int id,
            String name,
            String difficulty,
            double price,
            int deleted
    ) throws SQLException {
        this.assertIfRoomIdNotFound(id);
        Room room = (Room) this.repository.getById(id, EntityAttributes.room);
        room.setPrice(price);
        room.setName(name);
        room.setDifficulty(difficulty);
        room.setDeleted(deleted);
        room.setUpdated_at();
        this.repository.update(room, EntityAttributes.room);
    }


    public ArrayList<Room> getAllRoom(){
        ArrayList<Room> roomArrayList = new ArrayList<>();
        try{

            this.repository
                    .getAll(EntityAttributes.room)
                    .forEach(room -> roomArrayList.add((Room) room));
            return roomArrayList;
        }catch (SQLException e){
            logger.info(e.getMessage());
            return null;
        }
    }

    public void addObjectInRoom(int objectId, int roomId) {
        RepositroyRoomHasObjectDeco repo = new RepositroyRoomHasObjectDeco();
        try {
            repo.addRoomHasObjectDeco(roomId, objectId);
        } catch (SQLException e) {
            logger.info("Fail to fetch object in room");
        }
    }

    public ArrayList<ObjectDeco> getAllObjectsInRoom(int roomId) {
        RepositroyRoomHasObjectDeco repoRoomHasObject = new RepositroyRoomHasObjectDeco();
        try {
            return repoRoomHasObject.getAllObjectsByRoomId(roomId);
        } catch (SQLException e) {
            logger.info("Fail to get Objects in Room[id: " + roomId + "]");
        }
        return null;
    }

    public void addTipInRoom(int TipId, int roomId) {
        RepositoryRoomHasTips repo = new RepositoryRoomHasTips();
        try {
            repo.addRoomHasTips(roomId, TipId);
        } catch (SQLException e) {
            logger.info("Fail to fetch object in room");
        }
    }

    public ArrayList<Tips> getAllTipsInRoom(int roomId) {
        RepositoryRoomHasTips repoRoomHasTips = new RepositoryRoomHasTips();
        try {
            return repoRoomHasTips.getAllTipsByRoomId(roomId);
        } catch (SQLException e) {
            logger.info("Fail to get Objects in Room[id: " + roomId + "]");
        }
        return null;
    }
}
