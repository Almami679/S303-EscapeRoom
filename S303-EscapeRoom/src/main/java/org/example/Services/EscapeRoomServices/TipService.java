package org.example.Services.EscapeRoomServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.PlayerNotFound;
import org.example.Exceptions.TipNotFoundException;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.RoomEntities.Tips;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;
import org.example.Repository.Common.RepositoryImpl;
import org.example.Repository.RepositoryRelations.RepositoryRoomHasTips;

import java.sql.SQLException;
import java.util.ArrayList;

public class TipService {

    private static Logger logger = LogManager.getLogger(TipService.class);

    private final Repository repository;
    private final Entity entity = new Entity();
    private final RepositoryRoomHasTips repositoryRoomHasTips = new RepositoryRoomHasTips();



    public TipService() {
        this.repository = new RepositoryImpl();
    }

    private Tips castToTip(Entity entity) {
        Tips tip = null;
        if (entity instanceof Tips) {
            tip = (Tips) entity;
        }
        return tip;
    }

    public void createTip(
            String text
    ) {
        try {
            this.repository
                    .add(new Tips(text), EntityAttributes.tips);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public Tips getTipById(
            int id
    ) {
        try {
            return (Tips) this.repository
                    .getById(id, EntityAttributes.tips);
        } catch (SQLException e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public void deleteTip(
            int id
    ){
        try{
            Tips tips = (Tips) this.repository.getById(id, EntityAttributes.tips);

            if (tips == null) {
                throw new TipNotFoundException();
            } else {
                this.repository
                        .delete(id, EntityAttributes.tips);
            }
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
            Tips tips = (Tips) this.repository.getById(id, EntityAttributes.tips);
            if (tips == null) {
                throw new TipNotFoundException();
            } else {
                Tips tip = this.castToTip(entity);
                tip.setText(text);
                this.repository
                        .update(tip, EntityAttributes.tips);
        }
        }catch (SQLException | TipNotFoundException e){
            logger.info(e.getMessage());
        }
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
