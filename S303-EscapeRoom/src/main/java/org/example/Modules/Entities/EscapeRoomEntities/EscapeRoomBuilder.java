package org.example.Modules.Entities.EscapeRoomEntities;

import java.sql.Timestamp;

public class EscapeRoomBuilder {
    private int id;
    private String name;
    private double price;
    private String theme;
    private int deleted;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public EscapeRoomBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public EscapeRoomBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public EscapeRoomBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public EscapeRoomBuilder setTheme(String theme) {
        this.theme = theme;
        return this;
    }

    public EscapeRoomBuilder setDeleted(int deleted) {
        this.deleted = deleted;
        return this;
    }

    public EscapeRoomBuilder setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public EscapeRoomBuilder setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public EscapeRoom build() {
        return new EscapeRoom(id, name, price, theme, deleted, createdAt, updatedAt);
    }
}