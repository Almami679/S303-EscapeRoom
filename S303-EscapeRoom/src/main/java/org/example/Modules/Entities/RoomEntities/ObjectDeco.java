package org.example.Modules.Entities.RoomEntities;

import org.example.Modules.Entities.Entity;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ObjectDeco extends Entity {
    private String name;
    private String material;
    private double price;
    private int deleted;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ObjectDeco(String name, String material, double price, Timestamp createdAt, Timestamp updatedAt) {
        super();
        this.name = name;
        this.material = material;
        this.price = price;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ObjectDeco(int id,
                      String name,
                      String material,
                      double price,
                      int deleted,
                      Timestamp createdAt,
                      Timestamp updatedAt) {
        super(id,deleted);
        this.name = name;
        this.material = material;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    public void setUpdatedat() {
        this.updatedAt = (new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public String toString() {
        return "ObjectDecoTEST{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", material='" + material + '\'' +
                ", price=" + price +
                ", deleted=" + deleted +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public ArrayList<String> getValues(){
        ArrayList<String> values =  new ArrayList<>();
        String value = super.getId() + "";
        values.add(value);
        values.add(this.name);
        values.add(this.material);
        value = this.price +"";
        values.add(value);
        value = super.getDeleted()+"";
        values.add(value);
        values.add(this.createdAt.toString());
        values.add(this.updatedAt.toString());
        return values;
    }
}