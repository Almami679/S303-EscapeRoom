package org.example.Modules.Communicates.CommFactory;

import org.example.Modules.Entities.CommunicatesEntities.Notification;
import org.example.Modules.Entities.GameEntities.Player;

import static org.example.Repository.Old.SqlPlayerRepository.getPlayerById;


public class NotificationFactory implements CommFactoryInterface{
    @Override
    public Notification createCommunicate(int idPlayer) {
        Player player = getPlayerById(idPlayer);
        String text = "News flash!\n" +
                player.getName() +" You can't miss the new Christmas Escaperoom " +
                "from November 12 to January 10.\n" +
                "Notification sent to email: " + player.getEmail();

        return new Notification(player,text);
    }
}
