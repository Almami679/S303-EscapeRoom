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
    private Timestamp created_at;
    private Timestamp updated_at;


    public EscapeRoom(String name, Double price, String theme, Timestamp created_at, Timestamp updated_at) {
        super();
        this.name = name;
        this.price = price;
        this.theme = theme;
        this.deleted = 0;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public EscapeRoom(int id, String name, Double price, String theme, int deleted, Timestamp created_at, Timestamp updated_at) {
        super(id,deleted);
        this.id = id;
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

    public int getDeleted() {
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

    @Override
    public ArrayList<String> getValues() {
        ArrayList<String> values =  new ArrayList<>();
        String value = super.getId() + "";
        values.add(value);
        value = this.name+ "";
        values.add(value);
        values.add(String.valueOf(this.price));
        value = this.theme + "";
        values.add(value);
        values.add(this.deleted + "");
        value = this.created_at + "";
        values.add(value);
        value = this.updated_at + "";
        values.add(value);
        return values;
    }

    public void setUpdated_at(Timestamp escapeRoomUpdatedAt) {
        this.updated_at = escapeRoomUpdatedAt;
    }

    public void setCreated_at(Timestamp escapeRoomCreatedAt) {
        this.created_at = escapeRoomCreatedAt;
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