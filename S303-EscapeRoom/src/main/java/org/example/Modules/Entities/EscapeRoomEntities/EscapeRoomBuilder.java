package org.example.Modules.Entities.EscapeRoomEntities;

import java.sql.Timestamp;

public class EscapeRoomBuilder {
    private String name;
    private double price;
    private String theme;

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

    public EscapeRoom build() {
        return new EscapeRoom(name, price, theme);
    }
}