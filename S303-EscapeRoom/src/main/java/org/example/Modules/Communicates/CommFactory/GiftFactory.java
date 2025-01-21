package org.example.Modules.Communicates.CommFactory;

import org.example.Modules.Entities.CLASESTESTS.PlayerTEST;
import org.example.Modules.Entities.Communicates.Gift;

import static org.example.Repository.Old.SqlPlayerRepository.getPlayerById;


public class GiftFactory implements CommFactoryInterface{

    @Override
    public Gift createCommunicate(int idPlayer) {
        PlayerTEST player = getPlayerById(idPlayer);
        String text = "New Reward!\n" +
                player.getName() +" You have finished our horror " +
                "escape room without using clues\n "+
                "you have a gift sent to email " + player.getEmail();

        return new Gift(player,text);
    }
}
