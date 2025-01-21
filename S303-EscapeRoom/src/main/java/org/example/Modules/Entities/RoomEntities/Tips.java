package org.example.Modules.Entities.RoomEntities;

import org.example.Modules.Entities.Entity;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class Tips extends Entity {

    private static RepositoryImpl repositoryImpl = new RepositoryImpl();

    private String text;

    public Tips(String text) {
        super();
        this.text = text;
    }

    public Tips(int id, String text, int deleted) throws SQLException {
        super(id, deleted);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return "TipsTEST{" +
                "id=" + super.getId() +
                ", text='" + text + '\'' +
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
