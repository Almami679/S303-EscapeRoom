package org.example.Repository.Common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Entities.Entity;
import org.example.Repository.Serializers.Serializer;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.example.Repository.Serializers.Serializer.*;

public class RepositoryImpl implements Repository {

    private static Logger logger = LogManager.getLogger(RepositoryImpl.class);


    @Override
    public void add(Entity entity, EntityAttributes enumAttributes) throws SQLException {
        ArrayList<String> attributes = enumAttributes.getAttributes();
        ArrayList<String> values = entity.getValues();
        String tableName = enumAttributes.name();
        StringBuilder query = new StringBuilder("INSERT INTO escaperoomdb." + tableName + " (");
        for (int i = 0; i < attributes.size(); i++) {
            query.append(attributes.get(i));
            if (i < attributes.size() - 1) {
                query.append(", ");
            }
        }
        query.append(") VALUES (");
        for (int i = 0; i < values.size(); i++) {
            query.append("?");
            if (i < values.size() - 1) {
                query.append(", ");
            }
        }
        query.append(");");
        String queryString = query.toString();
        //logger.info("Query created, and casted to String.\n[" + queryString + "]");
        Serializer.serialize(queryString, enumAttributes, "add", values);
    }

    @Override
    public ArrayList<Entity> getAll(EntityAttributes enumAttributes) throws SQLException {
        String tableName = enumAttributes.name();
        String query = "SELECT * FROM escaperoomdb." + tableName + " WHERE " + tableName + "_deleted = 0;";
        return Serializer.deserializeGetAll(query, enumAttributes);
    }

    @Override
    public Entity getById(int id, EntityAttributes enumAttributes) throws SQLException {
        String tableName = enumAttributes.name();
        String query = "SELECT * FROM escaperoomdb." + tableName + " WHERE " + tableName + "_id = " + id + ";";
        return deserialize(query, enumAttributes);
    }

    @Override
    public void delete(int id, EntityAttributes enumAttributes) {
        String tableName = enumAttributes.name();
        String deletedAttribute = tableName + "_deleted";
        String idAttribute = tableName + "_id";
        String query = "UPDATE escaperoomdb." + tableName +
                " SET " + deletedAttribute + " = 1 " +
                "WHERE " + idAttribute + " = ?";
        ArrayList<String> values = new ArrayList<>();
        values.add(String.valueOf(id));
        Serializer.serialize(query, enumAttributes, "delete", values);
    }

    @Override
    public void update(Entity entity, EntityAttributes enumAttributes) {
        ArrayList<String> attributes = enumAttributes.getAttributes();
        ArrayList<String> values = entity.getValues();
        String tableName = enumAttributes.name();
        StringBuilder query = new StringBuilder("UPDATE escaperoomdb." + tableName + " SET ");
        for (int i = 0; i < attributes.size(); i++) {
            query.append(attributes.get(i)).append(" = ?");
            if (i < attributes.size() - 1) {
                query.append(", ");
            }
        }
        query.append(" WHERE ").append(attributes.get(0)).append(" = ?;");
        values.add(String.valueOf(entity.getId()));
        String queryString = query.toString();
        Serializer.serialize(queryString, enumAttributes, "update", values);
    }

    public void update1(Entity entity, EntityAttributes enumAttributes) {
        ArrayList<String> attributes = enumAttributes.getAttributes();
        ArrayList<String> values = entity.getValues();
        String tableName = enumAttributes.name();

        StringBuilder query = new StringBuilder("UPDATE escaperoomdb." + tableName + " SET ");

        for (int i = 0; i < attributes.size(); i++) {
            String attribute = attributes.get(i);
            String value = values.get(i);

            if (value.matches("\\d+")) { // Si es un número
                query.append(attribute).append(" = ").append(value);
            } else { // Si es texto
                query.append(attribute).append(" = '").append(value).append("'");
            }
            if (i < attributes.size() - 1) {
                query.append(", ");
            }
        }
        query.append(" WHERE ").append(attributes.get(0)).append(" = ").append(entity.getId()).append(";");

        String queryString = query.toString();
        logger.info("Query created and formatted:\n[" + queryString + "]");

        serializeUpdate(queryString, enumAttributes, "set", attributes);

    }
}