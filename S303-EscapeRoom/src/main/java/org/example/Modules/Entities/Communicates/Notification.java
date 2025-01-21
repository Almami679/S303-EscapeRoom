package org.example.Modules.Entities.Communicates;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Entities.CLASESTESTS.PlayerTEST;
import org.example.Modules.Communicates.CommunicationInterface;

import java.sql.Timestamp;
import java.util.ArrayList;


public class Notification extends Communicate implements CommunicationInterface {

    Logger logger = LogManager.getLogger(Notification.class);
    private String text;

    public Notification(PlayerTEST player, String text) {
        super(player);
        this.text = text;
    }

    public Notification(int id, int playerId, String text, Timestamp created_at) {
        super(id, playerId, created_at);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Notification setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public void send() {
        if(super.getPlayer().getConsentNotif() == 1) {
            logger.info("sending email to " + super.getPlayer().getEmail() +
                    " with Notification[id:" + super.getId() + "]");
        } else {
            logger.error("You don't have permissions to send Spam to playerId[" +
                    super.getPlayer().getId() + "]");
        }

    }

    @Override
    public ArrayList<String> getValues() {
        ArrayList<String> values =  new ArrayList<>();
        String value = super.getId() + "";
        values.add(value);
        value = super.getPlayer().getId() + "";
        values.add(value);
        values.add(this.text);
        values.add(super.getCreated_at().toString());
        return values;
    }

}
