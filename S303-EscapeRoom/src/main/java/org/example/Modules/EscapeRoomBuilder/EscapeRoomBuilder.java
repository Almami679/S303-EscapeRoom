package org.example.Modules.EscapeRoomBuilder;

import org.example.Repository.Common.EscapeRoomTEST;

import java.sql.Timestamp;
import java.util.ArrayList;

public class EscapeRoomBuilder {
    private String name;
    private ArrayList<ObjectDeco> decorations;
    private ArrayList<Tips> tips;
    private ArrayList<Room> rooms;
    private String theme;
    private Double price;
    private int deleted;
    private Timestamp created_at;
    private Timestamp updated_at;

    public EscapeRoomTEST build() {
        return new EscapeRoomTEST(name, price, theme, deleted, created_at, updated_at);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDecorations(ArrayList<ObjectDeco> decorations) {
        this.decorations = decorations;
    }

    public void setTips(ArrayList<Tips> tips) {
        this.tips = tips;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public void setCreatedAt(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setUpdatedAt(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}