package org.example.Modules.CLASESTESTS;

import java.sql.Timestamp;

public class EscapeRoomTEST {
    private static int latestId = 0;
    private int id;
    private String name;
    private Double price;
    private String theme;
    private boolean delete;
    private Timestamp created_at;
    private Timestamp updated_at;

    public EscapeRoomTEST(String name, Double price, String theme, boolean delete, Timestamp created_at, Timestamp updated_at) {
        this.id = ++latestId;
        this.name = name;
        this.price = price;
        this.theme = theme;
        this.delete = delete;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public static int getLatestId() {
        return latestId;
    }

    public static void setLatestId(int latestId) {
        EscapeRoomTEST.latestId = latestId;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTheme() {
        return theme;
    }

    public boolean isDeleted() {
        return delete;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "EscapeRoomTEST{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", theme='" + theme + '\'' +
                ", delete=" + delete +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
