package org.example.Modules.Entities.CLASESTESTS;

import org.example.Modules.Entities.Entity;

import java.sql.Timestamp;

public class RoomTEST extends Entity {

    private String name;
    private String difficulty;
    private Double price;
    private int escapeRoomId;
    private Timestamp created_at;
    private Timestamp updated_at;

    public RoomTEST(String name, String difficulty, Double price, int escapeRoomId, int deleted, Timestamp created_at, Timestamp updated_at) {
        super(deleted);
        this.name = name;
        this.difficulty = difficulty;
        this.price = price;
        this.escapeRoomId = escapeRoomId;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getEscapeRoomId() {
        return escapeRoomId;
    }

    public void setEscapeRoomId(int escapeRoomId) {
        this.escapeRoomId = escapeRoomId;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at() {
        this.updated_at = (new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public String toString() {
        return "RoomTEST{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", price=" + price +
                ", escapeRoomId=" + escapeRoomId +
                ", deleted=" + super.getDeleted() +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
