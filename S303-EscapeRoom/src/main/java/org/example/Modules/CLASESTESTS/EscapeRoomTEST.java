package org.example.Modules.CLASESTESTS;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;

public class EscapeRoomTEST {
    private static final Logger logger = LogManager.getLogger(EscapeRoomTEST.class);
    private int id;
    private String name;
    private Double price;
    private String theme;
    private int deleted;
    private Timestamp created_at;
    private Timestamp updated_at;

    public EscapeRoomTEST(String name, Double price, String theme, int deleted, Timestamp created_at, Timestamp updated_at) {
        this.name = name;
        this.price = price;
        this.theme = theme;
        this.deleted = deleted;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTheme() {
        return theme;
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

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at() {
        this.updated_at = (new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public String toString() {
        return "EscapeRoomTEST{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", theme='" + theme + '\'' +
                ", deleted=" + deleted +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}