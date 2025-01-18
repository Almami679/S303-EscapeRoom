package org.example.Modules.Entities.Communicates;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.Arrays;
import org.example.Modules.Entities.CLASESTESTS.GameTEST;
import org.example.Modules.Entities.CLASESTESTS.PlayerTEST;
import org.example.Modules.Communicates.CommunicationInterface;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import static org.example.Repository.Old.SqlGameRepository.getGameById;

public class Certificate extends Communicate implements CommunicationInterface {

    Logger logger = LogManager.getLogger(Certificate.class);
    private String text;
    private GameTEST game;


    public Certificate(PlayerTEST player, String text, GameTEST game) {
        super(player);
        this.game = game;
        this.text = text;
    }
    public Certificate(int id, int gameId, int playerId, String text, Timestamp created_at) {
        super(id, playerId, created_at);
        this.game = getGameById(gameId);
        this.text = text;
    }


    public String getText() {
        return this.text;
    }

    public GameTEST getGame() {
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
        value = this.game.getId() + "";
        values.add(value);
        values.add(this.text);
        values.add(super.getCreated_at().toString());
        value = super.getPlayer().getId() + "";
        values.add(value);
        return values;
     }
}
