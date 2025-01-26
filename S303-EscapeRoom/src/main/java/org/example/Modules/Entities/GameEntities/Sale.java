package org.example.Modules.Entities.GameEntities;

import org.example.Modules.Entities.Entity;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;
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

    public Sale(double price, Game game) {
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.price = price;
        this.gameId = game.getId();
        this.deleted = 0;
    }

    public Sale(int id, double price, int gameId, int deleted, Timestamp createdAt) throws SQLException {
        this.id = id;
        this.price = price;
        this.gameId = gameId;
        this.deleted = deleted;
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

    @Override
    public String toString() {
        return "SaleTEST{" +
                "id=" + id +
                ", created_at=" + createdAt +
                ", price=" + price +
                ", gameId=" + this.gameId +
                ", deleted=" + deleted +
                '}';
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
        return values;
    }


}
