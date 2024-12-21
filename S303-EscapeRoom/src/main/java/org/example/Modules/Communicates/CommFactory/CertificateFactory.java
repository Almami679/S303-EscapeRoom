package org.example.Modules.Communicates.CommFactory;

import org.example.Modules.Communicates.CLASESTEST.GameTEST;
import org.example.Modules.Communicates.CLASESTEST.PlayerTEST;
import org.example.Modules.Communicates.Certificate;


public class CertificateFactory implements CommFactoryInterface{
    @Override
    public Certificate createCommunicate(PlayerTEST player) {
        GameTEST game = player.getLastGame();
        String text = "Congratulations!\n" +
                player.getName() +" You have finished our " + game.getEscapeRoom() +
                "Escape Room \n "+
                "you have a Game Certificate sent to email " + player.getEmail();

        return new Certificate(player,text,game);
    }

}
