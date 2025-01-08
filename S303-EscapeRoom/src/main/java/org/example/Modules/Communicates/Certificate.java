package org.example.Modules.Communicates;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.CLASESTESTS.GameTEST;
import org.example.Modules.CLASESTESTS.PlayerTEST;

import java.util.Date;

public class Certificate extends Communicate implements CommunicationInterface{

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

    public String getText() {
        return this.text;
    }

    @Override
    public void send() {
        logger.info("sending Certificate to " + super.getPlayer().getEmail() + "\n" +
                "Game[" + this.game + "]\nFinished at[" +
                this.date + "]");

    }
}
