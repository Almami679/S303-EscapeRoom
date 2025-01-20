package org.example.Modules.Communicates.CommFactory;

import org.example.Modules.Entities.CLASESTESTS.GameTEST;
import org.example.Modules.Entities.CLASESTESTS.PlayerTEST;
import org.example.Modules.Entities.CommunicatesEntities.Certificate;

import static org.example.Repository.Old.SqlPlayerRepository.getPlayerById;


public class CertificateFactory implements CommFactoryInterface{
    @Override
    public Certificate createCommunicate(int idPlayer) {
        PlayerTEST player = getPlayerById(idPlayer);
        GameTEST game = player.getGame();
        String text = "Congratulations!\n" +
                player.getName() +" You have finished our " + game.getEscapeRoom() +
                "Escape Room \n "+
                "you have a Game Certificate sent to email " + player.getEmail();

        return new Certificate(player,text,game);

    }

}
