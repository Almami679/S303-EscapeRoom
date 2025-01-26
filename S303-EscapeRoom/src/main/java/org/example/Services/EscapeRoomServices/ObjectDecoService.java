package org.example.Services.EscapeRoomServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.ObjectDecoNotFoundException;
import org.example.Exceptions.PlayerNotFound;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Modules.Entities.RoomEntities.ObjectDeco;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class ObjectDecoService {
    private static Logger logger = LogManager.getLogger(RoomService.class);

    private final Repository repository;
    private final Entity entity = new Entity();


    public ObjectDecoService() {
        this.repository = new RepositoryImpl();
    }

    private ObjectDeco castToDecoObject(Entity entity) {
        ObjectDeco decoObject = null;
        if (entity instanceof ObjectDeco) {
            decoObject = (ObjectDeco) entity;
        }
        return decoObject;
    }



    private void assertIfObjectDecoIdNotFound(int id) throws SQLException {
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

    public ObjectDeco getObjectDecoById(
            int id
    ) {
        try {
            ObjectDeco objectDeco = (ObjectDeco) this.repository.getById(id, EntityAttributes.objectdeco);
            if (objectDeco == null) {
                throw new ObjectDecoNotFoundException();
            } else {
                return (ObjectDeco) repository
                        .getById(id, EntityAttributes.objectdeco);
            }
        } catch (SQLException | ObjectDecoNotFoundException e ) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public void deleteObjectDeco(
            int id
    ){
        try{
            ObjectDeco objectDeco = (ObjectDeco) this.repository.getById(id, EntityAttributes.objectdeco);
            if (objectDeco == null) {
                throw new ObjectDecoNotFoundException();
            } else {
                this.repository
                        .delete(id, EntityAttributes.objectdeco);
            }
        }catch (ObjectDecoNotFoundException | SQLException e){
            logger.info(e.getMessage());
        }
    }

    //Todo verificar estos metodos
    public void updateObjectDeco(
            int id,
            String name,
            String material,
            double price
    )  {
        try{
            ObjectDeco objectDeco = (ObjectDeco) this.repository.getById(id, EntityAttributes.objectdeco);
            if (objectDeco == null) {
                throw new ObjectDecoNotFoundException();
            } else {
                objectDeco.setPrice(price);
                objectDeco.setName(name);
                objectDeco.setMaterial(material);
                this.repository
                        .update(objectDeco, EntityAttributes.objectdeco);
            }
        }catch (SQLException e){
            logger.info(e.getMessage());
        }

    }


    public ArrayList<ObjectDeco> getAllObjectDeco() {
        ArrayList<ObjectDeco> objectDecoArrayList = new ArrayList<>();
        try {

            this.repository
                    .getAll(EntityAttributes.objectdeco)
                    .forEach(objectDeco -> objectDecoArrayList.add((ObjectDeco) objectDeco));
            return objectDecoArrayList;
        } catch (SQLException e) {
            logger.info(e.getMessage());
            return null;
        }
    }
}
