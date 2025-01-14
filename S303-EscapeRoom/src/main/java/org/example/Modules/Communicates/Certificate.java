package org.example.Modules.Communicates;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.CLASESTESTS.GameTEST;
import org.example.Modules.CLASESTESTS.PlayerTEST;

import java.sql.Timestamp;
import java.util.Date;

public class Certificate extends Communicate implements CommunicationInterface{

    Logger logger = LogManager.getLogger(Certificate.class);
    private int id;
    private String text;
    private GameTEST game;

    public Certificate(PlayerTEST player, String text, GameTEST game) {
        super(player);
        this.game = game;
        this.text = text;
    }

    public int getId(){
        return this.id;
    }

    public int gameId() {
        return this.game.getId();
    }

    public GameTEST getGame(){
        return this.game;
    }

    public Timestamp getDate(){
        return this.game.getGameDate();
    }

    public String getText() {
        return this.text;
    }

    @Override
    public void send() {
        logger.info("sending Certificate to " + super.getPlayer().getEmail() + "\n" +
                "Game[" + this.game.getEscapeRoom().getName() +
                "(" + this.game.getEscapeRoom().getTheme() + ")" +
                "]\nFinished at[" + this.getDate() + "]");
    }
}
