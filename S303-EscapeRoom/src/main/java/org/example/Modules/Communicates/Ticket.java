package org.example.Modules.Communicates;

import org.example.Modules.CLASESTESTS.PlayerTEST;
import org.example.Modules.CLASESTESTS.SaleTEST;

public class Ticket extends Communicate implements CommunicationInterface {

    private String text;
    private SaleTEST sale;

    public Ticket(PlayerTEST player, String text, SaleTEST sale) {
        super(player);
        this.text = text;
        this.sale = sale;

    }

    public String getText() {
        return text;
    }

    public Ticket setText(String text) {
        this.text = text;
        return this;
    }

    public SaleTEST getSale() {
        return sale;
    }

    public Ticket setSale(SaleTEST sale) {
        this.sale = sale;
        return this;
    }


    @Override
    public void send() {
        System.out.println("sending to email " + super.getPlayer().getEmail() + "\n" +
                "Ticket[id:" + super.getId() + "]");

    }
}
