package org.example.Modules.Entities.CommunicatesEntities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Communicates.CommunicationInterface;
import org.example.Modules.Entities.GameEntities.Player;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;


public class Notification extends Communicate implements CommunicationInterface {

    Logger logger = LogManager.getLogger(Notification.class);
    private String text;

    public Notification(int playerId, String text) {
        super(playerId);
        this.text = text;
    }

    public Notification(int id, int playerId, String text, Timestamp created_at) throws SQLException {
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
    public String toString() {
        return "Notification{" +
                "id= " + super.getId() +
                "Player= " + super.getPlayer() +
                "text='" + text + '\'' +
                '}';
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
        values.add(super.getCreatedAt().toString());
        return values;
    }

}
