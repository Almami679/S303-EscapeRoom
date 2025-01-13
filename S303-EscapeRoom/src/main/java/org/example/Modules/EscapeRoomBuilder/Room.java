package org.example.Modules.EscapeRoomBuilder;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Room {
    private String name;
    private ArrayList<ObjectDeco> objectsList;
    private ArrayList<Tips> tipsList;
    private String difficulty;
    private double price;
    private static int idCounter=0;
    private int id;
    private Timestamp created_at;
    private Timestamp updated_at;

    Room(String name, String difficulty, double price,Timestamp created_at, Timestamp updated_at){
        idCounter++;
        this.id = idCounter;
        this.name = name;
        this.difficulty = difficulty;
        this.objectsList = new ArrayList<>();
        this.tipsList = new ArrayList<>();
        this.price = price;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ObjectDeco> getObjectsList() {
        return objectsList;
    }

    public ArrayList<Tips> getTipsList() {
        return tipsList;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void addTip(Tips tip){
        tipsList.add(tip);
    }
    public void removeTip(Tips tip){
        tipsList.remove(tip);
    }

    public void addObjectDeco(ObjectDeco objectDeco){
        objectsList.add(objectDeco);
    }

    public void removeObjectDeco(ObjectDeco objectDeco){
        objectsList.remove(objectDeco);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", objectsList=" + objectsList +
                ", tipsList=" + tipsList +
                ", difficulty='" + difficulty + '\'' +
                ", price=" + price +
                ", id=" + id +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
