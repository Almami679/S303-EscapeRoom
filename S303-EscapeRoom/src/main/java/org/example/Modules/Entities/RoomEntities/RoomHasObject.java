package org.example.Modules.Entities.RoomEntities;

import org.example.Modules.Entities.Entity;

import java.util.ArrayList;

public class RoomHasObject extends Entity {

    private int idObject;
    private int idRoom;

    public RoomHasObject(int idObject, int idRoom) {
        this.idObject = idObject;
        this.idRoom = idRoom;
    }

    public int getIdObject() {
        return idObject;
    }

    public int getIdRoom() {
        return idRoom;
    }

    @Override
    public ArrayList<String> getValues() {
        ArrayList<String> values =  new ArrayList<>();
        String value = this.idRoom + "";
        values.add(value);
        value = this.idObject + "";
        values.add(value);
        return values;
    }
}
