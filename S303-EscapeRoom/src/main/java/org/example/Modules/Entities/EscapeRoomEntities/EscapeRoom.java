package org.example.Modules.Entities.EscapeRoomEntities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Entities.Entity;

import java.sql.Timestamp;
import java.util.ArrayList;

public class EscapeRoom extends Entity {
    private static final Logger logger = LogManager.getLogger(EscapeRoom.class);
    private int id;
    private String name;
    private Double price;
    private String theme;
    private int deleted;
    private Timestamp createdAt;
    private Timestamp updatedAt;


    public EscapeRoom(String name, Double price, String theme, Timestamp createdAt, Timestamp updatedAt) {
        super();
        this.name = name;
        this.price = price;
        this.theme = theme;
        this.deleted = 0;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public EscapeRoom(int id, String name, Double price, String theme, int deleted, Timestamp createdAt, Timestamp updatedAt) {
        super(id,deleted);
        this.id = id;
        this.name = name;
        this.price = price;
        this.theme = theme;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public ArrayList<String> getValues() {
        ArrayList<String> values = new ArrayList<>();
        values.add(String.valueOf(this.getId()));
        values.add(this.name);
        values.add(String.valueOf(this.price));
        values.add(this.theme);
        values.add(String.valueOf(this.getDeleted()));
        values.add(String.valueOf(this.createdAt));
        values.add(String.valueOf(this.updatedAt));
        return values;
    }

    public void setUpdatedAt(Timestamp escapeRoomUpdatedAt) {
        this.updatedAt = escapeRoomUpdatedAt;
    }

    public void setCreatedAt(Timestamp escapeRoomCreatedAt) {
        this.createdAt = escapeRoomCreatedAt;
    }

    @Override
    public String toString() {
        return "EscapeRoomTEST{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", theme='" + theme + '\'' +
                ", deleted=" + deleted +
                ", created_at=" + createdAt +
                ", updated_at=" + updatedAt +
                '}';
    }
}