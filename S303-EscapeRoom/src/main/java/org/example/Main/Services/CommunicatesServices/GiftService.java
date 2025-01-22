package org.example.Main.Services.CommunicatesServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.GiftNotFoundException;
import org.example.Exceptions.PlayerNotFound;
import org.example.Modules.Communicates.CommFactory.CommunicateFactory;
import org.example.Modules.Communicates.CommunicateType;
import org.example.Modules.Entities.CommunicatesEntities.Gift;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;

import java.sql.SQLException;
import java.util.ArrayList;

public class GiftService {
    private static final Logger logger = LogManager.getLogger(GiftService.class);

    private final CommunicateFactory mainFactory = new CommunicateFactory();
    private final Repository repository;
    private final Entity entity = new Entity();


    public GiftService(Repository repository) {
        this.repository = repository;
    }

    private Gift castToGift(Entity entity) {
        Gift gift = null;
        if (entity instanceof Gift) {
            gift = (Gift) entity;
        }
        return gift;
    }



    private void assertIfGiftIdNotFound(int id) {
        this.repository
                .getAll(EntityAttributes.gift)
                .stream()
                .map(this::castToGift)
                .forEach(gift -> {
                    if (gift.getId() != id) {
                        throw new GiftNotFoundException("Gift with id: " + id + " not found");
                    }
                });
    }

    public void createGift(
            int playerId
    ) {
        try {
            Gift gift = (Gift) mainFactory.createCommunicate(CommunicateType.GIFT, playerId);

            this
                    .repository
                    .add(gift, EntityAttributes.gift);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void getGiftById(
            int id
    ) {
        try {
            this.assertIfGiftIdNotFound(id);
            this.repository
                    .getById(id, EntityAttributes.gift);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void deleteGift(
            int id
    ){
        try{
            this.assertIfGiftIdNotFound(id);
            this.repository
                    .delete(id, EntityAttributes.gift);
        }catch (PlayerNotFound e){
            logger.info(e.getMessage());
        }
    }

    //Todo verificar estos metodos
    public void updateGift(
            int id,
            Player player,
            String text
    ) throws SQLException {
        this.assertIfGiftIdNotFound(id);

        Gift gift = this.castToGift(entity);
        gift.setPlayer(player);
        gift.setText(text);

        this.repository
                .update(gift, EntityAttributes.gift);
    }

    //Todo verificar estos metodos
    public ArrayList<Gift> getAllGift(){
        ArrayList<Gift> giftArrayList = new ArrayList<>();
        this.repository
                .getAll(EntityAttributes.gift)
                .forEach(gift -> giftArrayList.add((Gift) gift));
        return giftArrayList;
    }
}
