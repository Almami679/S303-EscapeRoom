package org.example.Modules.Communicates.CommFactory;

import org.example.Modules.Entities.CommunicatesEntities.Certificate;
import org.example.Modules.Entities.GameEntities.Game;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;
import org.example.Services.GameServices.GameService;
import org.example.Services.GameServices.PlayerService;

import java.sql.SQLException;


public class CertificateFactory implements CommFactoryInterface{
    private final PlayerService playerService = new PlayerService();
    @Override
    public Certificate createCommunicate(int playerId) {
        Game game = playerService.getPlayerById(playerId).getLastGame();
        String text = "Congratulations!\n" +
                playerService.getPlayerById(playerId).getName() +" You have finished our " + game.getEscapeRoom().getName() +
                "Escape Room \n "+
                "you have a Game Certificate sent to email " + playerService.getPlayerById(playerId).getEmail();

        return new Certificate(playerId,text,game.getId());

    }

}
