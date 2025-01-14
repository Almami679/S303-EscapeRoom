package org.example.Modules.CLASESTESTS;

public class TipsTEST {
    private int id;
    private String text;
    private int roomId;

    public TipsTEST(String text, int roomId) {
        this.text = text;
        this.roomId = roomId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "TipsTEST{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", roomId=" + roomId +
                '}';
    }
}