package org.example.Modules.Entities;

import java.util.ArrayList;

public class Entity {
    private int deleted;
    private int id;

    public Entity (int id, int deleted) {
        this.id = id;
        this.deleted = deleted;
    }
    public ArrayList<String> getValues(){
        return null;
    }
    public void delete() {
        this.deleted = 1;
    }
    public void restore() {
        this.deleted = 0;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
       this.id = id;
    }
}
