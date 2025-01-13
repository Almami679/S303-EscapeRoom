package org.example.Modules.CLASESTESTS;

import org.example.Modules.EscapeRoomBuilder.EscapeRoom;
import org.example.Modules.EscapeRoomBuilder.ObjectDeco;
import org.example.Modules.EscapeRoomBuilder.Room;
import org.example.Modules.EscapeRoomBuilder.Tips;

import java.sql.Timestamp;
import java.util.ArrayList;

public class EscapeRoomBuilderTEST {
    private String name;
    private Double price;
    private String theme;
    private int deleted;
    private Timestamp created_at;
    private Timestamp updated_at;


    public EscapeRoomTEST build(){
        return new EscapeRoomTEST(name, price, theme, deleted, created_at, updated_at);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
