package org.example.Modules.CLASESTESTS;

import org.example.Repository.DatabaseConnection;

public class TipsTEST {
    private int id;
    private String text;
    private int roomId;

    public TipsTEST(String text, int roomId) {
        this.text = text;
        this.roomId = roomId;
    }

    private int getLatestIdFromDB() {
        DatabaseConnection db = new DatabaseConnection();
        return db.getLatestTipsId() + 1;
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