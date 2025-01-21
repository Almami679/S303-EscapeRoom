package org.example.Modules.Entities.GameEntities;

import org.example.Modules.Entities.Entity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

public class Player extends Entity {

    private String name;
    private String email;
    private int consentNotif;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private ArrayList<Game> completedGames;
    private ArrayList<Sale> playerSales;


    public Player(
            String name,
            String email,
            int consentNotif
    ) {
        super();
        this.name = name;
        this.email = email;
        this.createdAt = Timestamp.from(Instant.now());
        this.updatedAt = Timestamp.from(Instant.now());
        this.consentNotif = consentNotif;
        this.completedGames = new ArrayList<>();
        this.playerSales = new ArrayList<>();
    }

    public Player(int id,
                  String name,
                  String email,
                  int consentNotif,
                  int deleted,
                  Timestamp createdAt,
                  Timestamp updatedAt) {
        super(id, deleted);
        this.consentNotif = consentNotif;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getConsentNotif() {
        return consentNotif;
    }

    public void setConsentNotif(int consentNotif) {
        this.consentNotif = consentNotif;
    }

    public void addSale(Sale sale) {
        this.playerSales.add(sale);
    }

    public Game getGame() {
        return this.completedGames.getLast();
    }

    public Sale getSale() {
        return this.playerSales.getLast();
    }

    public void addGame(Game game) {
        this.completedGames.add(game);
    }

    @Override
    public String toString() {
        return "PlayerTEST{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", consentNotif=" + consentNotif +
                ", deleted=" + super.getDeleted() +
                '}';
    }

    @Override
    public ArrayList<String> getValues() {
        ArrayList<String> values = new ArrayList<>();
        String value = super.getId() + "";
        values.add(value);
        values.add(name);
        values.add(email);
        value = this.consentNotif + "";
        values.add(value);
        value = super.getDeleted() + "";
        values.add(value);
        values.add(createdAt.toString());
        values.add(updatedAt.toString());
        return values;
    }
}