package org.example.Services.CommunicatesServices;

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
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class GiftService {
    private static final Logger logger = LogManager.getLogger(GiftService.class);

    private final CommunicateFactory mainFactory = new CommunicateFactory();
    private final Repository repository;
    private final Entity entity = new Entity();


    public GiftService() {
        this.repository = new RepositoryImpl();
    }


    public void createGift(
            int player
    ) {
        try {
            Gift gift = (Gift) mainFactory.createCommunicate(CommunicateType.GIFT, player);

            this
                    .repository
                    .add(gift, EntityAttributes.gift);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public Gift getGiftById(
            int id
    ) {
        try {
            Gift gift = (Gift) this.repository.getById(id, EntityAttributes.gift);

            if (gift == null) {
                throw new GiftNotFoundException("Gift with id " + id + " not found");
            } else {

            return (Gift) this.repository
                    .getById(id, EntityAttributes.gift);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public void deleteGift(
            int id
    ){
        try{
            Gift gift = (Gift) this.repository.getById(id, EntityAttributes.gift);
            if (gift == null) {
                throw new GiftNotFoundException("Player with id " + id + " not found");
            } else {
                this.repository
                        .delete(id, EntityAttributes.gift);
            }
        }catch (GiftNotFoundException | SQLException e){
            logger.info(e.getMessage());
        }
    }

    public void updateGift(
            int id,
            int player,
            String text
    )  {
        try {

            Gift gift = (Gift) this.repository.getById(id, EntityAttributes.gift);
            if (gift == null) {
                throw new GiftNotFoundException("Player with id " + id + " not found");
            } else {
                gift.setPlayer(player);
                gift.setText(text);
                this.repository
                        .update(gift, EntityAttributes.gift);
            }
        }catch (SQLException e){
            logger.info(e.getMessage());
        }

    }

    public ArrayList<Gift> getAllGift(){
        ArrayList<Gift> giftArrayList = new ArrayList<>();
        try{

        this.repository
                .getAll(EntityAttributes.gift)
                .forEach(gift -> giftArrayList.add((Gift) gift));
        return giftArrayList;
        }catch (SQLException e){
            logger.info(e.getMessage());
            return null;
        }
    }
}
