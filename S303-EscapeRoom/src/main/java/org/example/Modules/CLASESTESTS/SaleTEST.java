package org.example.Modules.CLASESTESTS;

import java.sql.Timestamp;

public class SaleTEST {

    /*private double price;
    private int id;

    public SaleTEST() {
        this.price = Math.random()*500;
    }

    public double getPrice() {
        return price;
    }

    public SaleTEST setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getId() {
        return id;
    }

    public SaleTEST setId(int id) {
        this.id = id;
        return this;
    }*/
    private int id;
    private Timestamp date;
    private double price;
    private int gameId;
    private int deleted;

    public SaleTEST(Timestamp date, double price, int gameId, int deleted) {
        this.date = date;
        this.price = price;
        this.gameId = gameId;
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
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
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
                ", gameId=" + gameId +
                ", deleted=" + deleted +
                '}';
    }
}
