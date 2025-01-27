package org.example.Modules.Entities.EscapeRoomEntities;

import org.example.observers.Observer;

import java.sql.Timestamp;

public class EscapeRoomBuilder {
    private String name;
    private double price;
    private String theme;
    private final EscapeRoomNotifier notifier;

    public EscapeRoomBuilder(EscapeRoomNotifier notifier) {
        this.notifier = notifier;
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

    public EscapeRoom build() {
        EscapeRoom escapeRoom = new EscapeRoom(name, price, theme);
        return escapeRoom;
    }

    public void addObserver(Observer observer) {
        notifier.addObserver(observer);
    }
}