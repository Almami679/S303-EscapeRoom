package org.example.Modules.Entities.GameEntities;

import org.example.Modules.Entities.Entity;
import org.example.Services.GameServices.GameService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Sale extends Entity {

    private final GameService gameService = new GameService();

    private int id;
    private Timestamp createdAt;
    private double price;
    private int gameId;
    private int deleted;

    public Sale(double price, int gameId) {
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.price = price;
        this.gameId = gameId;
        this.deleted = 0;
    }

    public Sale(double price) {
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.price = price;
        this.deleted = 0;
    }

    public Sale(int id, double price, int gameId, int deleted, Timestamp createdAt) throws SQLException {
        super(id, deleted);
        this.price = price;
        this.gameId = gameId;
        this.createdAt = createdAt;
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

    public int getGameId(){
        return this.gameId;
    }

    public Game getGame() {
        return gameService.getGameById(this.gameId);
    }

    public void setGame(int gameId) throws SQLException {
        this.gameId = gameId;
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


    public String toStringSQL() {
        return "SaleTEST{" +
                "id=" + id +
                ", created_at=" + createdAt +
                ", price=" + price +
                ", gameId=" + this.gameId +
                ", deleted=" + deleted +
                '}';
    }

    @Override
    public String toString() {
        return
                " Date: '" + createdAt + '\'' +
                ", GameId: '" + gameId + '\'' +
                ", Price: '" + price + "€" + '\'';
    }

    public ArrayList<String> getValues() {
        ArrayList<String> values =  new ArrayList<>();
        String value = super.getId() + "";
        values.add(value);
        value = this.price + "";
        values.add(value);
        value = this.gameId +"";
        values.add(value);
        value = super.getDeleted() + "";
        values.add(value);
        values.add(createdAt.toString());
        return values;
    }


}
