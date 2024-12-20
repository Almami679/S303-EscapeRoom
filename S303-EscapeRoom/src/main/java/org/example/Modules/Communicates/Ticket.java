package org.example.Modules.Communicates;

import org.example.Modules.Communicates.CLASESTEST.PlayerTEST;
import org.example.Modules.Communicates.CLASESTEST.SaleTEST;
import org.example.Modules.Communicates.CommFactory.CommunicationInterface;

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


    }
}
