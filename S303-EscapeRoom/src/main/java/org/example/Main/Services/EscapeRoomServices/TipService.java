package org.example.Main.Services.EscapeRoomServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.PlayerNotFound;
import org.example.Exceptions.TipNotFoundException;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.RoomEntities.Room;
import org.example.Modules.Entities.RoomEntities.Tips;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;

import java.sql.SQLException;
import java.util.ArrayList;

public class TipService {

    private static Logger logger = LogManager.getLogger(RoomService.class);

    private final Repository repository;
    private final Entity entity = new Entity();


    public TipService(Repository repository) {
        this.repository = repository;
    }

    private Tips castToTip(Entity entity) {
        Tips tip = null;
        if (entity instanceof Room) {
            tip = (Tips) entity;
        }
        return tip;
    }



    private void assertIfTipIdNotFound(int id) throws SQLException {
        this.repository
                .getAll(EntityAttributes.tips)
                .stream()
                .map(this::castToTip)
                .forEach(tip -> {
                    if (tip.getId() != id) {
                        throw new TipNotFoundException();
                    }
                });
    }

    public void createTip(
            String text
    ) {
        try {
            this
                    .repository
                    .add(new Tips(text), EntityAttributes.tips);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void getTipById(
            int id
    ) {
        try {
            this.assertIfTipIdNotFound(id);
            this.repository
                    .getById(id, EntityAttributes.tips);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void deleteTip(
            int id
    ){
        try{
            this.assertIfTipIdNotFound(id);
            this.repository
                    .delete(id, EntityAttributes.tips);
        }catch (PlayerNotFound | SQLException e){
            logger.info(e.getMessage());
        }
    }

    //Todo verificar estos metodos
    public void updateTip(
            int id,
            String text
    )  {
        try {
            
        this.assertIfTipIdNotFound(id);
        }catch (SQLException e){
            logger.info(e.getMessage());
        }

        Tips tip = this.castToTip(entity);
        tip.setText(text);

        this.repository
                .update(tip, EntityAttributes.tips);
    }

    public ArrayList<Tips> getAllTips(){
        ArrayList<Tips> tipsArrayList = new ArrayList<>();
        try{

        this.repository
                .getAll(EntityAttributes.tips)
                .forEach(tips -> tipsArrayList.add((Tips) tips));
        return tipsArrayList;
        }catch (SQLException e){
            logger.info(e.getMessage());
            return null;
        }
    }
}
