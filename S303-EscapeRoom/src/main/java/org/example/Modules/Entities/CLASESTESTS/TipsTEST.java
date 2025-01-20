package org.example.Modules.Entities.CLASESTESTS;

import org.example.Modules.Entities.Entity;

import java.util.ArrayList;

public class TipsTEST extends Entity {
    private String text;
    private RoomTEST room;

    public TipsTEST(String text, RoomTEST room, int deleted) {
        super(deleted);
        this.text = text;
        this.room = room;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRoomId() {
        return room.getId();
    }

    public void setRoom(RoomTEST room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "TipsTEST{" +
                "id=" + super.getId() +
                ", text='" + text + '\'' +
                ", roomId=" + room.getId() +
                '}';
    }

    public ArrayList<String> getValues(){
        ArrayList<String> values =  new ArrayList<>();
        String value = super.getId() + "";
        values.add(value);
        values.add(this.text);
        value = super.getDeleted() + "";
        values.add(value);
        return values;
    }

}
