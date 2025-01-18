package org.example.Modules.Entities.CLASESTESTS;

import org.example.Modules.Entities.Communicates.Entity;

import java.sql.Timestamp;

import static org.example.Repository.Old.SqlGameRepository.getGameById;

public class SaleTEST extends Entity {

    private int id;
    private Timestamp date;
    private double price;
    private GameTEST game;
    private int deleted;

    public SaleTEST(Timestamp date, double price, GameTEST game, int deleted) {
        this.date = date;
        this.price = price;
        this.game = game;
        this.deleted = deleted;
    }

    public SaleTEST(int id, Timestamp date, double price, int gameId, int deleted) {
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
