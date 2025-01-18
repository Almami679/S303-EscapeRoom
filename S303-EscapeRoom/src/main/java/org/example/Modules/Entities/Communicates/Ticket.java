package org.example.Modules.Entities.Communicates;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Entities.CLASESTESTS.PlayerTEST;
import org.example.Modules.Entities.CLASESTESTS.SaleTEST;
import org.example.Modules.Communicates.CommunicationInterface;

import java.sql.Timestamp;

import static org.example.Repository.Old.SqlSaleRepository.getSaleById;


public class Ticket extends Communicate implements CommunicationInterface {

    Logger logger = LogManager.getLogger(Ticket.class);
    private String text;
    private SaleTEST sale;
    private int id;
    private Timestamp created_at;

    public Ticket(PlayerTEST player, String text, SaleTEST sale) {
        super(player);
        this.text = text;
        this.sale = sale;
        this.created_at = new Timestamp(System.currentTimeMillis());

    }

    public Ticket(int id, int playerId, int saleId, String text, Timestamp created_at) {
        super(id, playerId);
        this.sale = getSaleById(saleId);
        this.text = text;
        this.created_at = created_at;
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
