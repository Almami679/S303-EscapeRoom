/*package org.example.Modules.Communicates;

import org.example.Modules.CLASESTESTS.PlayerTEST;


public class Notification extends Communicate implements CommunicationInterface {

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
        System.out.println(super.getPlayer().getConsentNotif()
                ?
                "sending email with Notification[id:" + super.getId() + "]"
                :
                "You do not have permissions to send Spam");
    }
}*/
