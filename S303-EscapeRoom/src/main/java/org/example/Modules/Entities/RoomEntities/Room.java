package org.example.Modules.Entities.RoomEntities;

import org.example.Modules.Entities.Entity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

public class Room extends Entity {

    private String name;
    private String difficulty;
    private Double price;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private ArrayList<ObjectDeco> ObjectsInRoom;

    public Room(String name,
                String difficulty,
                Double price
    ) {
        super();
        this.name = name;
        this.difficulty = difficulty;
        this.price = price;
        this.createdAt = Timestamp.from(Instant.now());
        this.updatedAt = Timestamp.from(Instant.now());
        this.ObjectsInRoom = new ArrayList<>();
    }

    public Room(int id,
                String name,
                String difficulty,
                Double price,
                int deleted,
                Timestamp createdAt,
                Timestamp updatedAt
    ) {
        super(id, deleted);
        this.name = name;
        this.difficulty = difficulty;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt() {
        this.updatedAt = (new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public String toString() {
        return "RoomTEST{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", price=" + price +
                ", deleted=" + super.getDeleted() +
                ", created_at=" + createdAt +
                ", updated_at=" + updatedAt +
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
        values.add(this.createdAt.toString());
        values.add(this.updatedAt.toString());
        return values;
    }
}
