package org.example.Modules.Entities.RoomEntities;

import org.example.Modules.Entities.Entity;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class Tips extends Entity {

    private static RepositoryImpl repositoryImpl = new RepositoryImpl();

    private String text;
    private Room room;

    public Tips(String text, Room room) {
        super();
        this.text = text;
        this.room = room;
    }

    public Tips(int id, String text, int roomId, int deleted) throws SQLException {
        super(id, deleted);
        this.text = text;
        this.room = (Room) repositoryImpl.getById(roomId, EntityAttributes.room);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRoomId() {
        return room.getId();
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "TipsTEST{" +
                "id=" + super.getId() +
                ", text='" + text + '\'' +
                ", roomId=" + room.getId() +
                '}';
    }

    public ArrayList<String> getValues(){
        ArrayList<String> values =  new ArrayList<>();
        String value = super.getId() + "";
        values.add(value);
        values.add(this.text);
        value = super.getDeleted() + "";
        values.add(value);
        return values;
    }

}
