package org.example.Modules.Entities.EscapeRoomEntities;

import org.example.Modules.Entities.Entity;

import java.util.ArrayList;

public class EscapeRoomHasRoom extends Entity {
    private int idEscapeRoom;
    private int idRoom;

    public EscapeRoomHasRoom(int idEscapeRoom, int idRoom) {
        this.idEscapeRoom = idEscapeRoom;
        this.idRoom = idRoom;
    }

    public int getIdEscapeRoom() {
        return idEscapeRoom;
    }

    public int getIdRoom() {
        return idRoom;
    }

    @Override
    public ArrayList<String> getValues() {
        ArrayList<String> values =  new ArrayList<>();
        String value = this.idRoom + "";
        values.add(value);
        value = this.idEscapeRoom + "";
        values.add(value);
        return values;
    }
}
