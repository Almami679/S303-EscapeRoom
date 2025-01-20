package org.example.Modules.Communicates.CommFactory;

import org.example.Modules.Entities.CommunicatesEntities.Notification;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;



public class NotificationFactory implements CommFactoryInterface{
    RepositoryImpl repository = new RepositoryImpl();
    @Override
    public Notification createCommunicate(int idPlayer) throws SQLException {
        Player player = (Player) repository.getById(idPlayer, EntityAttributes.player);
        String text = "News flash!\n" +
                player.getName() +" You can't miss the new Christmas Escaperoom " +
                "from November 12 to January 10.\n" +
                "Notification sent to email: " + player.getEmail();

        return new Notification(player,text);
    }
}
