package org.example.Modules.Entities.CLASESTESTS;

import org.example.Modules.Entities.Entity;

import java.sql.Timestamp;
import java.util.ArrayList;

public class EscapeRoomBuilder {
    private String name;
    private String theme;
    private Double price;
    private int deleted;
    private Timestamp created_at;
    private Timestamp updated_at;

    public EscapeRoom build() {
        return new EscapeRoom(name, price, theme, deleted, created_at, updated_at);
    }

    public void setName(String name) {
        this.name = name;
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