package org.example.Modules.Communicates;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.CLASESTESTS.PlayerTEST;
import org.example.Modules.CLASESTESTS.SaleTEST;

import static org.example.Repository.SqlSaleRepository.getSaleById;


public class Ticket extends Communicate implements CommunicationInterface {

    Logger logger = LogManager.getLogger(Ticket.class);
    private String text;
    private SaleTEST sale;
    private int id;

    public Ticket(PlayerTEST player, String text, SaleTEST sale) {
        super(player);
        this.text = text;
        this.sale = sale;

    }

    public Ticket(int id, int playerId, int saleId, String text) {
        super(id, playerId);
        this.sale = getSaleById(saleId);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Ticket setText(String text) {
        this.text = text;
        return this;
    }

    public int getId() {
        return this.id;
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
        logger.info("sending Ticket to " + super.getPlayer().getEmail() + "\n" +
                "Ticket[id: " + super.getId() + " || Value: " + getSale().getPrice() + "€]");

    }
}
