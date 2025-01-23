package org.example.Services.EscapeRoomServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.EscapeRoomNotFoundException;
import org.example.Exceptions.PlayerNotFound;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoomBuilder;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class EscapeRoomService {
    private static Logger logger = LogManager.getLogger(RoomService.class);
    private final EscapeRoomBuilder builder = new EscapeRoomBuilder();
    private final Repository repository;
    private final Entity entity = new Entity();


    public EscapeRoomService() {
        this.repository = new RepositoryImpl();
    }

    private EscapeRoom castToEscapeRoom(Entity entity) {
        EscapeRoom escapeRoom = null;
        if (entity instanceof EscapeRoom) {
            escapeRoom = (EscapeRoom) entity;
        }
        return escapeRoom;
    }



    private void assertIfEscapeRoomIdNotFound(int id) throws SQLException {
        this.repository
                .getAll(EntityAttributes.escaperoom)
                .stream()
                .map(this::castToEscapeRoom)
                .forEach(escaperoom -> {
                    if (escaperoom.getId() != id) {
                        throw new EscapeRoomNotFoundException();
                    }
                });
    }

    public void createEscapeRoom(
            String name,
            double price,
            String theme
    ) {
        try {
            builder.setName(name);
            builder.setPrice(price);
            builder.setTheme(theme);
            EscapeRoom escaperoom = builder.build();
            this
                    .repository
                    .add(escaperoom, EntityAttributes.escaperoom);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void getEscapeRoomById(
            int id
    ) {
        try {
            this.assertIfEscapeRoomIdNotFound(id);
            this.repository
                    .getById(id, EntityAttributes.escaperoom);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void deleteEscapeRoom(
            int id
    ){
        try{
            this.assertIfEscapeRoomIdNotFound(id);
            this.repository
                    .delete(id, EntityAttributes.escaperoom);
        }catch (PlayerNotFound | SQLException e){
            logger.info(e.getMessage());
        }
    }

    //Todo verificar estos metodos
    public void updateEscapeRoom(
            int id,
            String name,
            String theme,
            double price
    ) {
        try {

        this.assertIfEscapeRoomIdNotFound(id);
        }catch (SQLException e){
            logger.info(e.getMessage());
        }

        EscapeRoom escaperoom = this.castToEscapeRoom(entity);
        escaperoom.setPrice(price);
        escaperoom.setName(name);
        escaperoom.setTheme(theme);
        this.repository
                .update(escaperoom, EntityAttributes.escaperoom);
    }

    //Todo verificar estos metodos
    public ArrayList<EscapeRoom> getAllEscapeRooms(){
        ArrayList<EscapeRoom> outputList = new ArrayList<>();
        try {

        this.repository
                .getAll(EntityAttributes.escaperoom).forEach(entity -> outputList.add((EscapeRoom) entity));
        return outputList;
        }catch (SQLException e){
            logger.info(e.getMessage());
            return null;
        }
    }
}
