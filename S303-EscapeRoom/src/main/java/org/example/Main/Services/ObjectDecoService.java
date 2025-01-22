package org.example.Main.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.ObjectDecoNotFoundException;
import org.example.Exceptions.PlayerNotFound;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.RoomEntities.ObjectDeco;
import org.example.Modules.Entities.RoomEntities.Room;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;

import java.sql.SQLException;

public class ObjectDecoService {
    private static Logger logger = LogManager.getLogger(RoomService.class);

    private final Repository repository;
    private final Entity entity = new Entity();


    public ObjectDecoService(Repository repository) {
        this.repository = repository;
    }

    private ObjectDeco castToDecoObject(Entity entity) {
        ObjectDeco decoObject = null;
        if (entity instanceof ObjectDeco) {
            decoObject = (ObjectDeco) entity;
        }
        return decoObject;
    }



    private void assertIfObjectDecoIdNotFound(int id) {
        this.repository
                .getAll(EntityAttributes.objectdeco)
                .stream()
                .map(this::castToDecoObject)
                .forEach(object -> {
                    if (object.getId() != id) {
                        throw new ObjectDecoNotFoundException();
                    }
                });
    }

    public void createObjectDeco(
            String name,
            String material,
            double price

    ) {
        try {
            this
                    .repository
                    .add(new ObjectDeco(name, material, price), EntityAttributes.objectdeco);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void getObjectDecoById(
            int id
    ) {
        try {
            this.assertIfObjectDecoIdNotFound(id);
            this.repository
                    .getById(id, EntityAttributes.objectdeco);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void deleteObjectDeco(
            int id
    ){
        try{
            this.assertIfObjectDecoIdNotFound(id);
            this.repository
                    .delete(id, EntityAttributes.objectdeco);
        }catch (PlayerNotFound e){
            logger.info(e.getMessage());
        }
    }

    //Todo verificar estos metodos
    public void updateObjectDeco(
            int id,
            String name,
            String material,
            double price
    ) throws SQLException {
        this.assertIfObjectDecoIdNotFound(id);

        ObjectDeco objectDeco = this.castToDecoObject(entity);
        objectDeco.setPrice(price);
        objectDeco.setName(name);
        objectDeco.setMaterial(material);

        this.repository
                .update(objectDeco, EntityAttributes.objectdeco);
    }

    //Todo verificar estos metodos
    public void getAllObjectDeco(){
        this.repository
                .getAll(EntityAttributes.objectdeco);
    }
}
