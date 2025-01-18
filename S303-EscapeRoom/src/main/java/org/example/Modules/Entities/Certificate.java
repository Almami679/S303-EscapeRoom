package org.example.Modules.Entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.CLASESTESTS.GameTEST;
import org.example.Modules.CLASESTESTS.PlayerTEST;
import org.example.Modules.Communicates.Communicate;
import org.example.Modules.Communicates.CommunicationInterface;

import java.util.Date;

import static org.example.Repository.Old.SqlGameRepository.getGameById;

public class Certificate extends Communicate implements CommunicationInterface {

    Logger logger = LogManager.getLogger(Certificate.class);
    private String text;
    private GameTEST game;
    private Date date;

    public Certificate(PlayerTEST player, String text, GameTEST game) {
        super(player);
        this.game = game;
        this.text = text;
        this.date = new Date();
    }
    public Certificate(int id, int gameId, int playerId, String text) {
        super(id, playerId);
        this.game = getGameById(gameId);
        this.text = text;
        this.date = this.game.getGameDate();
    }


    public String getText() {
        return this.text;
    }

    public GameTEST getGame() {
        return game;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public void send() {
        logger.info("sending Certificate to " + super.getPlayer().getEmail() + "\n" +
                "Game[" + this.game + "]\nFinished at[" +
                this.date + "]");

    }
}
