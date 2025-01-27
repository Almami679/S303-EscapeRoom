package org.example.Modules.Entities.CommunicatesEntities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Entities.GameEntities.Sale;
import org.example.Modules.Communicates.CommunicationInterface;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;
import org.example.Services.GameServices.SaleService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;


public class Ticket extends Communicate implements CommunicationInterface {

    Logger logger = LogManager.getLogger(Ticket.class);
    private final SaleService saleService = new SaleService();
    private String text;
    private int saleId;


    public Ticket(int playerId, String text, int sale) {
        super(playerId);
        this.text = text;
        this.saleId = sale;

    }

    public Ticket(int id, int playerId, int saleId, String text, Timestamp created_at) throws SQLException {
        super(id, playerId, created_at);
        this.saleId = saleId;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Ticket setText(String text) {
        this.text = text;
        return this;
    }
    public void setSale(int saleId) {
        this.saleId = saleId;
    }

    public Sale getSale() {
        return saleService.getSaleById(saleId);
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "id= " + super.getId() +
                "Player= " + super.getPlayer() +
                "text='" + text + '\'' +
                ", saleId=" + saleId +
                '}';
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
        value = this.saleId + "";
        values.add(value);
        values.add(this.text);
        value = super.getPlayer().getId()+"";
        values.add(value);
        values.add(super.getCreatedAt().toString());
        return values;
    }
}
