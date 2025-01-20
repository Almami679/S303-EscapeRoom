package org.example.Modules.Communicates.CommFactory;

import org.example.Modules.Entities.CommunicatesEntities.Gift;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;



public class GiftFactory implements CommFactoryInterface{
    RepositoryImpl repository = new RepositoryImpl();

    @Override
    public Gift createCommunicate(int idPlayer) throws SQLException {
        Player player = (Player) repository.getById(idPlayer, EntityAttributes.player);
        String text = "New Reward!\n" +
                player.getName() +" You have finished our horror " +
                "escape room without using clues\n "+
                "you have a gift sent to email " + player.getEmail();

        return new Gift(player,text);
    }
}
