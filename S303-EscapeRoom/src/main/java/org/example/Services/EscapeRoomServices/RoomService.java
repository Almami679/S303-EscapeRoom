package org.example.Services.EscapeRoomServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.PlayerNotFound;
import org.example.Exceptions.RoomNotFoundException;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.RoomEntities.Room;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;
import org.example.Repository.Common.RepositoryImpl;
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
        this.repository
                .getAll(EntityAttributes.room)
                .stream()
                .map(this::castToRoom)
                .forEach(room -> {
                    if (room.getId() != id) {
                        throw new RoomNotFoundException();
                    }
                });
    }

    public void createRoom(
            String name,
            double price,
            String dificulty
    ) {
        try {
            this
                    .repository
                    .add(new Room(name, dificulty, price), EntityAttributes.room);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void getRoomById(
            int id
    ) {
        try {
            this.assertIfRoomIdNotFound(id);
            this.repository
                    .getById(id, EntityAttributes.room);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void deleteRoom(
            int id
    ){
        try{
            this.assertIfRoomIdNotFound(id);
            this.repository
                    .delete(id, EntityAttributes.room);
        }catch (PlayerNotFound | SQLException e){
            logger.info(e.getMessage());
        }
    }

    //Todo verificar estos metodos
    public void updateRoom(
            int id,
            String name,
            String dificutly,
            double price
    ) throws SQLException {
        this.assertIfRoomIdNotFound(id);

        Room room = this.castToRoom(entity);
        room.setPrice(price);
        room.setName(name);
        room.setDifficulty(dificutly);

        this.repository
                .update(room, EntityAttributes.room);
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
        this.repository = (RepositroyRoomHasObjectDeco) this.repository;
        repository.addRoomHasObjectDeco(roomId, objectId);
    }
}
