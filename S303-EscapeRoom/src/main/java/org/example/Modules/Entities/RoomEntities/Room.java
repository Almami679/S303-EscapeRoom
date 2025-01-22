package org.example.Modules.Entities.RoomEntities;

import org.example.Modules.Entities.Entity;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Room extends Entity {

    private String name;
    private String difficulty;
    private Double price;
    private Timestamp created_at;
    private Timestamp updated_at;
    private ArrayList<ObjectDeco> ObjectsInRoom;

    public Room(String name,
                String difficulty,
                Double price

    ) {
        super();
        this.name = name;
        this.difficulty = difficulty;
        this.price = price;
        this.created_at = new Timestamp(System.currentTimeMillis());
        this.updated_at = new Timestamp(System.currentTimeMillis());
        this.ObjectsInRoom = new ArrayList<>();
    }

    public Room(int id,
                String name,
                String difficulty,
                Double price,
                int deleted,
                Timestamp created_at,
                Timestamp updated_at
    ) {
        super(id, deleted);
        this.name = name;
        this.difficulty = difficulty;
        this.price = price;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.ObjectsInRoom = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addObjectInRoom(ObjectDeco object) {
        this.ObjectsInRoom.add(object);
    }

    public ArrayList<ObjectDeco> getObjectsInRoom() {
        return this.ObjectsInRoom;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at() {
        this.updated_at = (new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public String toString() {
        return "RoomTEST{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", price=" + price +
                ", deleted=" + super.getDeleted() +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }

    public ArrayList<String> getValues(){
        ArrayList<String> values =  new ArrayList<>();
        String value = super.getId() + "";
        values.add(value);
        values.add(this.name);
        values.add(this.difficulty);
        value = this.price +"";
        values.add(value);
        value = super.getDeleted()+"";
        values.add(value);
        values.add(this.created_at.toString());
        values.add(this.updated_at.toString());
        return values;
    }
}
