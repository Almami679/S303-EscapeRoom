package org.example.Modules.Communicates.CommFactory;

import org.example.Modules.Entities.CommunicatesEntities.Notification;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;
import org.example.Repository.Common.RepositoryImpl;
import org.example.Services.GameServices.PlayerService;

import java.sql.SQLException;



public class NotificationFactory implements CommFactoryInterface{
    private static final PlayerService playerService = new PlayerService();    @Override
    public Notification createCommunicate(int playerId) {

        String text = "News flash!\n" +
                playerService.getPlayerById(playerId).getName() +" You can't miss the new Christmas Escaperoom " +
                "from November 12 to January 10.\n" +
                "Notification sent to email: " + playerService.getPlayerById(playerId).getEmail();

        return new Notification(playerId,text);
    }
}
