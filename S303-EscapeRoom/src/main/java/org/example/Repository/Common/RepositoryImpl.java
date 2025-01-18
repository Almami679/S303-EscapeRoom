package org.example.Repository.Common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Entities.Entity;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.example.Repository.Serializer.serialize;

public class RepositoryImpl <T> implements Repository{

    private static Logger logger = LogManager.getLogger(RepositoryImpl.class);
    private static DatabaseConnection dbConnection;

    @Override
    public void add(Entity entity, EntityAttributes enumAttributes) throws SQLException {
        ArrayList<String> attributes = enumAttributes.getAttributes();
        ArrayList<String> values = entity.getValues();
        String tableName = enumAttributes.name();
        StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " (");
        attributes.forEach(attribute -> {
            if(attribute.equals(attributes.getLast())) {
                query.append(") VALUES (");
            } else {
                query.append(attribute +", ");
            }
        });
        values.forEach(value -> {
            if(value.equals(values.getLast())) {
                query.append(")");
            } else {
                query.append(value +", ");
            }
        });
        String queryString = query.toString();
        serialize(queryString, enumAttributes, dbConnection);
    }

    @Override
    public Entity getById(int id, EntityAttributes enumAttributes) {
        String tableName = enumAttributes.name();
        String attribute = enumAttributes.getAttributes().getFirst();
        String query = "SELECT * FROM " + tableName + " WHERE " + attribute + " = " + id;
        return null;
    }

    @Override
    public void delete(int id, EntityAttributes enumAttributes) {

    }

    @Override
    public void update(Entity entity, EntityAttributes enumAttributes) {

    }

    @Override
    public ArrayList<Entity> getAll(EntityAttributes enumAttributes) {

        return null;
    }

}
