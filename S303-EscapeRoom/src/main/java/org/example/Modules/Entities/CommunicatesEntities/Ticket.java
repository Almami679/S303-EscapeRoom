package org.example.Modules.Entities.CommunicatesEntities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Entities.CLASESTESTS.SaleTEST;
import org.example.Modules.Communicates.CommunicationInterface;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import static org.example.Repository.Old.SqlSaleRepository.getSaleById;


public class Ticket extends Communicate implements CommunicationInterface {

    Logger logger = LogManager.getLogger(Ticket.class);
    private static RepositoryImpl repositoryImpl = new RepositoryImpl();
    private String text;
    private SaleTEST sale;


    public Ticket(Player player, String text, SaleTEST sale) {
        super(player);
        this.text = text;
        this.sale = sale;

    }

    public Ticket(int id, int playerId, int saleId, String text, Timestamp created_at) throws SQLException {
        super(id, playerId, created_at);
        this.sale = (SaleTEST) repositoryImpl.getById(saleId, EntityAttributes.sale);
        this.text = text;
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
        logger.info("sending Ticket to " + super.getPlayer().getEmail() + "\n" +
                "Ticket[id: " + super.getId() + " || Value: " + getSale().getPrice() + "€]");

    }

    @Override
    public ArrayList<String> getValues() {
        ArrayList<String> values =  new ArrayList<>();
        String value = super.getId() + "";
        values.add(value);
        value = this.sale.getId() + "";
        values.add(value);
        values.add(this.text);
        value = super.getPlayer().getId()+"";
        values.add(value);
        values.add(super.getCreated_at().toString());
        return values;
    }
}
