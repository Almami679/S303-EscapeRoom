package org.example.Modules.Entities.GameEntities;

import org.example.Modules.Entities.Entity;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Sale extends Entity {

    RepositoryImpl repository = new RepositoryImpl();

    private int id;
    private Timestamp createdAt;
    private double price;
    private Game game;
    private int deleted;

    public Sale(double price, Game game) {
        this.createdAt = game.getGameDate();
        this.price = price;
        this.game = game;
    }

    public Sale(int id, double price, int gameId, int deleted) throws SQLException {
        this.id = id;
        this.price = price;
        this.game = (Game) repository.getById(gameId, EntityAttributes.game);
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getGameId() {
        return game.getId();
    }

    public void setGame(int gameId) throws SQLException {
        this.game = (Game) repository.getById(gameId, EntityAttributes.game);
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Sale setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "SaleTEST{" +
                "id=" + id +
                ", created_at=" + createdAt +
                ", price=" + price +
                ", gameId=" + this.game.getId() +
                ", deleted=" + deleted +
                '}';
    }

    public ArrayList<String> getValues() {
        ArrayList<String> values =  new ArrayList<>();
        String value = super.getId() + "";
        values.add(value);
        value = this.price + "";
        values.add(value);
        value = this.getGameId() +"";
        values.add(value);
        value = super.getDeleted() + "";
        values.add(value);
        return values;
    }


}
