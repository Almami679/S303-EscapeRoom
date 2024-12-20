package org.example.Modules.Communicates;

import org.example.Modules.Communicates.CLASESTEST.PlayerTEST;


public class Notification extends Communicate {

    private String text;

    public Notification(PlayerTEST player, String text) {
        super(player);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Notification setText(String text) {
        this.text = text;
        return this;
    }
}
