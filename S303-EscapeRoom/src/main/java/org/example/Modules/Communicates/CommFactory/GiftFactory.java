package org.example.Modules.Communicates.CommFactory;

import org.example.Modules.Entities.CommunicatesEntities.Gift;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;
import org.example.Services.GameServices.GameService;
import org.example.Services.GameServices.PlayerService;

import java.sql.SQLException;



public class GiftFactory implements CommFactoryInterface{
    private static final PlayerService playerService = new PlayerService();
    private static final GameService gameService = new GameService();
    @Override
    public Gift createCommunicate(int playerId) {
        int gameId = gameService.getLastGameByPlayer(playerId).getId();
        String text = "New Reward!\n" +
                playerService.getPlayerById(playerId).getName() +" You have finished our horror " +
                "escape room without using clues\n "+
                "you have a gift sent to email " + playerService.getPlayerById(playerId).getEmail();

        return new Gift(playerId,text,gameId);
    }
}
