package org.example.Repository.Common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Entities.Entity;
import org.example.Repository.Serializers.Serializer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.example.Repository.Serializers.Serializer.deserialize;

public class RepositoryImpl implements Repository{

    private static Logger logger = LogManager.getLogger(RepositoryImpl.class);
    //private static DatabaseConnection dbConnection;

    /*public RepositoryImpl(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }*/
    @Override
    public void insert(Entity entity, EntityAttributes enumAttributes) throws SQLException {
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
        logger.info("Query created, and casted to String.\n[" + queryString + "]");
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
        String attribute = enumAttributes.getAttributes().getFirst();
        String query = "SELECT * FROM escaperoomdb." + tableName + " WHERE " + attribute + " = " + id + ";";
        return deserialize(query, enumAttributes);
    }

    @Override
    public void delete(int id, EntityAttributes enumAttributes) {
        String tableName = enumAttributes.name();
        String deletedAttribute = tableName + "_deleted";
        String idAttribute = enumAttributes.getAttributes().get(0);
        String query = "UPDATE escaperoomdb." + tableName +
                " SET " + deletedAttribute + " = 1 " +
                "WHERE " + idAttribute + " = ?";
        ArrayList<String> values = new ArrayList<>();
        values.add(String.valueOf(id));
        Serializer.serialize(query, enumAttributes, "delete", values);
    }

    @Override
    public void update(Entity entity, EntityAttributes enumAttributes) {

        ///UPDATE escaperoomdb.certificate
        ///SET Certificate_text = "Segunda Prueba de update",
        ///Certificate_gameId = 3,
        ///Certificate_playerId = 3
        ///WHERE Certificate_id = 1;

        ArrayList<String> attributes = enumAttributes.getAttributes();
        ArrayList<String> values = entity.getValues();
        String tableName = enumAttributes.name();

        ///Consultar esto del atomic

        AtomicInteger pos = new AtomicInteger();
        StringBuilder query = new StringBuilder("UPDATE escaperoomdb." + tableName + " SET ");
        attributes.forEach(attribute -> {
            if(attribute.equals(attributes.getLast())) {
                query.append(attribute + " = " + values.getLast() +
                        " WHERE " + enumAttributes.getAttributes().getFirst() +
                        " = " + entity.getId() + ";");
            } else {
                query.append(attribute + " = " + values.get(pos.get()) + ", ");
                pos.getAndIncrement();
            }
        });
        String queryString = query.toString();
        logger.info("Query created, and casted to String.\n[" + queryString + "]");
        //serialize(queryString, enumAttributes, "set");
    }

}
