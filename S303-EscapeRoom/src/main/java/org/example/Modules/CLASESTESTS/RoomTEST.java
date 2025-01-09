package org.example.Modules.CLASESTESTS;

import org.example.Repository.DatabaseConnection;

import java.sql.Timestamp;

public class RoomTEST {
    private int id;
    private String name;
    private String difficulty;
    private Double price;
    private int escapeRoomId;
    private int deleted;
    private Timestamp created_at;
    private Timestamp updated_at;

    public RoomTEST(String name, String difficulty, Double price, int escapeRoomId, int deleted, Timestamp created_at, Timestamp updated_at) {
        this.id = getLatestIdFromDB();
        this.name = name;
        this.difficulty = difficulty;
        this.price = price;
        this.escapeRoomId = escapeRoomId;
        this.deleted = deleted;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    private int getLatestIdFromDB() {
        DatabaseConnection db = new DatabaseConnection();
        return db.getLatestRoomId() + 1;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    public int isDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
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

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "RoomTEST{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", price=" + price +
                ", escapeRoomId=" + escapeRoomId +
                ", deleted=" + deleted +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
