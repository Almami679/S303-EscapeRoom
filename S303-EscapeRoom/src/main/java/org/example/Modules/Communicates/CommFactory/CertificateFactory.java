package org.example.Modules.Communicates.CommFactory;

import org.example.Modules.Entities.CommunicatesEntities.Certificate;
import org.example.Modules.Entities.GameEntities.Game;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;


public class CertificateFactory implements CommFactoryInterface{
    RepositoryImpl repositoryImpl = new RepositoryImpl();
    @Override
    public Certificate createCommunicate(int idPlayer) throws SQLException {
        Player player = (Player) repositoryImpl.getById(idPlayer, EntityAttributes.player);
        Game game = player.getGame();
        String text = "Congratulations!\n" +
                player.getName() +" You have finished our " + game.getEscapeRoom() +
                "Escape Room \n "+
                "you have a Game Certificate sent to email " + player.getEmail();

        return new Certificate(player,text,game);

    }

}
