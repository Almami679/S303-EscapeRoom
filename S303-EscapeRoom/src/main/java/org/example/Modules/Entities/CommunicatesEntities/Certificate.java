package org.example.Modules.Entities.CommunicatesEntities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Communicates.CommunicationInterface;
import org.example.Modules.Entities.GameEntities.Game;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import static org.example.Repository.Old.SqlGameRepository.getGameById;

public class Certificate extends Communicate implements CommunicationInterface {

    private static RepositoryImpl repositoryImpl = new RepositoryImpl();

    Logger logger = LogManager.getLogger(Certificate.class);
    private String text;
    private Game game;


    public Certificate(Player player, String text, Game game) {
        super(player);
        this.game = game;
        this.text = text;
    }
    public Certificate(int id,
                       int gameId,
                       int playerId,
                       String text,
                       Timestamp created_at) throws SQLException {
        super(id, playerId, created_at);
        this.game = (Game) repositoryImpl.getById(gameId, EntityAttributes.game);
        this.text = text;
    }


    public String getText() {
        return this.text;
    }

    public Game getGame() {
        return game;
    }

    public Timestamp getGameDate() {
        return this.game.getGameDate();
    }

    @Override
    public void send() {
        logger.info("sending Certificate to " + super.getPlayer().getEmail() + "\n" +
                "Game[" + this.game + "]\nFinished at[" +
                this.getGameDate() + "]");

    }
    @Override
     public ArrayList<String> getValues() {
        ArrayList<String> values =  new ArrayList<>();
        String value = super.getId() + "";
        values.add(value);
        value = super.getId() + "";
        values.add(value);
        values.add(this.text);
        values.add(super.getCreated_at().toString());
        value = super.getPlayer().getId() + "";
        values.add(value);
        return values;
     }
}
