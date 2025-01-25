package org.example.Modules.Entities.RoomEntities;

import org.example.Modules.Entities.Entity;

import java.util.ArrayList;

public class RoomHasTips extends Entity {
    private int idTip;
    private int idRoom;

    public RoomHasTips(int idTip, int idRoom) {
        this.idTip = idTip;
        this.idRoom = idRoom;
    }

    public int getIdObject() {
        return idTip;
    }

    public int getIdRoom() {
        return idRoom;
    }

    @Override
    public ArrayList<String> getValues() {
        ArrayList<String> values =  new ArrayList<>();
        String value = this.idRoom + "";
        values.add(value);
        value = this.idTip + "";
        values.add(value);
        return values;
    }
}
