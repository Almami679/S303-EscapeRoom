package org.example.Modules.CLASESTESTS;

import org.example.Repository.DatabaseConnection;

import java.sql.Timestamp;

public class ObjectDecoTEST {
    private int id;
    private String name;
    private String material;
    private int roomId;
    private double price;
    private int deleted;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ObjectDecoTEST(String name, String material, int roomId, double price, int deleted, Timestamp createdAt, Timestamp updatedAt) {
        //this.id = getLatestIdFromDB();
        this.name = name;
        this.material = material;
        this.roomId = roomId;
        this.price = price;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private int getLatestIdFromDB() {
        DatabaseConnection db = new DatabaseConnection();
        return db.getLatestObjectDecoId() + 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedat() {
        this.updatedAt = (new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public String toString() {
        return "ObjectDecoTEST{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", material='" + material + '\'' +
                ", roomId=" + roomId +
                ", price=" + price +
                ", deleted=" + deleted +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}