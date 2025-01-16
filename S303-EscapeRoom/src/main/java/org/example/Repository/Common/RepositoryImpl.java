package org.example.Repository.Common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.CLASESTESTS.GameTEST;
import org.example.Repository.Serializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositoryImpl <T> implements Repository{

    private static Logger logger = LogManager.getLogger(RepositoryImpl.class);
    private static DatabaseConnection dbConnection;
    private Serializer serializer;

    private Object castEntity(Object entity, EntityAttributes entityAttribute) {
        switch (entityAttribute){
            case GAME -> {
                return entity;
            }
        }

    }

    @Override
    public void add(Object entity, EntityAttributes enumAttributes) {
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
        try (Connection connection = dbConnection.dbConnect();
             PreparedStatement statement = connection.prepareStatement(query.toString())) {
            logger.info(tableName + " added. [Id: " + values.getFirst() + "]");
        } catch (SQLException e) {
            logger.error("Failed to add " + tableName + ": ", e);
        }
    }

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public ArrayList getAll() {
        return null;
    }

}
