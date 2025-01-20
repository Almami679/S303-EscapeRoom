package org.example.Modules.Entities.EscapeRoomEntities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Entities.Entity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

public class EscapeRoom extends Entity {
    // quitado el logger, los loggers no deben ir en las clases modelos
    private int id;
    private String name;
    private Double price;
    private String theme;
    private int deleted;
    private Timestamp createdAt;
    private Timestamp updatedAt;


    public EscapeRoom(
            String name,
            Double price,
            String theme
    ) {
        super();
        this.name = name;
        this.price = price;
        this.theme = theme;
        this.deleted = 0;
        this.createdAt = Timestamp.from(Instant.now());
        this.updatedAt = Timestamp.from(Instant.now());
    }

    public EscapeRoom(
            int id,
            String name,
            Double price,
            String theme,
            int deleted,
            Timestamp createdAt,
            Timestamp updatedAt
    ) {
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
        ArrayList<String> values =  new ArrayList<>();
        String value = super.getId() + "";
        values.add(value);
        value = this.name+ "";
        values.add(value);
        values.add(String.valueOf(this.price));
        value = this.theme + "";
        values.add(value);
        values.add(this.deleted + "");
        value = this.createdAt + "";
        values.add(value);
        value = this.updatedAt + "";
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