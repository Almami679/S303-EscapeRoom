package org.example.Modules.Communicates;

import org.example.Modules.Communicates.CLASESTEST.GameTEST;
import org.example.Modules.Communicates.CLASESTEST.PlayerTEST;

import java.util.Date;

public class Certificate extends Communicate implements CommunicationInterface{

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
        System.out.println("sending to email " + super.getPlayer().getEmail() + "\n" +
                "Game[" + this.game + "]\nFinished at[" +
                this.date + "]");

    }
}
