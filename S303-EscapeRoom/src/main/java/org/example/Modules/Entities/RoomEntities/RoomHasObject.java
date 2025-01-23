package org.example.Modules.Entities.RoomEntities;

import org.example.Modules.Entities.Entity;

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
}
