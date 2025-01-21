package org.example.Modules.Entities.GameEntities;

import org.example.Modules.Entities.Entity;

import java.sql.Timestamp;

import static org.example.Repository.Old.SqlGameRepository.getGameById;

public class Sale extends Entity {

    private int id;
    private Timestamp date;
    private double price;
    private Game game;
    private int deleted;

    public Sale(Timestamp date, double price, Game game, int deleted) {
        this.date = date;
        this.price = price;
        this.game = game;
        this.deleted = deleted;
    }

    public Sale(int id, Timestamp date, double price, int gameId, int deleted) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.game = getGameById(gameId);
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
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

    public void setGame(int gameId) {
        this.game = getGameById(gameId);
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
                ", date=" + date +
                ", price=" + price +
                ", gameId=" + game.getId() +
                ", deleted=" + deleted +
                '}';
    }
}
