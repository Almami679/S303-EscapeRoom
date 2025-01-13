package org.example.Modules.Communicates.CommFactory;

import org.example.Modules.CLASESTESTS.PlayerTEST;
import org.example.Modules.Communicates.Gift;


public class GiftFactory implements CommFactoryInterface{

    @Override
    public Gift createCommunicate(PlayerTEST player) {
        String text = "New Reward!\n" +
                player.getName() +" You have finished our horror " +
                "escape room without using clues\n "+
                "you have a gift sent to email " + player.getEmail();

        return new Gift(player,text);
    }
}
