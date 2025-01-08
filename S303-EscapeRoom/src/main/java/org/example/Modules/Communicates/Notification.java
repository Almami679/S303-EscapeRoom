package org.example.Modules.Communicates;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.CLASESTESTS.PlayerTEST;


public class Notification extends Communicate implements CommunicationInterface {

    Logger logger = LogManager.getLogger(Notification.class);
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

    @Override
    public void send() {
        if(super.getPlayer().getConsentNotif()) {
            logger.info("sending email to " + super.getPlayer().getEmail() +
                    " with Notification[id:" + super.getId() + "]");
        } else {
            logger.error("You don't have permissions to send Spam to playerId[" +
                    super.getPlayer().getId() + "]");
        }

    }
}
