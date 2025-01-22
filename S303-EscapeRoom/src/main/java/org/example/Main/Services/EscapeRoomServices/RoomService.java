package org.example.Main.Services.EscapeRoomServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.PlayerNotFound;
import org.example.Exceptions.RoomNotFoundException;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.RoomEntities.Room;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;

import java.sql.SQLException;

public class RoomService {

    private static Logger logger = LogManager.getLogger(RoomService.class);

    private final Repository repository;
    private final Entity entity = new Entity();


    public RoomService(Repository repository) {
        this.repository = repository;
    }

    private Room castToRoom(Entity entity) {
        Room room = null;
        if (entity instanceof Room) {
            room = (Room) entity;
        }
        return room;
    }



    private void assertIfRoomIdNotFound(int id) {
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
        }catch (PlayerNotFound e){
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
                .update(room, EntityAttributes.sale);
    }

    //Todo verificar estos metodos
    public void getAllRoom(){
        this.repository
                .getAll(EntityAttributes.room);
    }
}
