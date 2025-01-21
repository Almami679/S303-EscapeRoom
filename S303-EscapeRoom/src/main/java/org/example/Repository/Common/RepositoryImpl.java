package org.example.Repository.Common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Entities.Entity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.example.Repository.Serializer.deserialize;
import static org.example.Repository.Serializer.serialize;

public class RepositoryImpl implements Repository{

    private static Logger logger = LogManager.getLogger(RepositoryImpl.class);
    private static DatabaseConnection dbConnection;

    @Override
    public void add(Entity entity, EntityAttributes enumAttributes) throws SQLException {
        ArrayList<String> attributes = enumAttributes.getAttributes();
        ArrayList<String> values = entity.getValues();
        String tableName = enumAttributes.name();
        StringBuilder query = new StringBuilder("INSERT INTO escaperomdb." + tableName + " (");
        attributes.forEach(attribute -> {
            if(attribute.equals(attributes.getLast())) {
                query.append(") VALUES (");
            } else {
                query.append(attribute +", ");
            }
        });
        values.forEach(value -> {
            if(value.equals(values.getLast())) {
                query.append(");");
            } else {
                query.append(value +", ");
            }
        });
        String queryString = query.toString();
        logger.info("Query created, and casted to String.\n[" + queryString + "]");
        serialize(queryString, enumAttributes, dbConnection, "add");
    }

    @Override
    public Entity getById(int id, EntityAttributes enumAttributes) throws SQLException {
        String tableName = enumAttributes.name();
        String attribute = enumAttributes.getAttributes().getFirst();
        String query = "SELECT * FROM escaperomdb." + tableName + " WHERE " + attribute + " = " + id + ";";
        return deserialize(query, enumAttributes, dbConnection);
    }

    @Override
    public void delete(int id, EntityAttributes enumAttributes) {
        String tableName = enumAttributes.name();
        String attribute = tableName + "_deleted";
        String query = "UPDATE escaperomdb." + tableName +
                " SET " + attribute + " = 1 " +
                "WHERE " + enumAttributes.getAttributes().getFirst() + " = " + id + ";";
        serialize(query, enumAttributes, dbConnection, "delete");

        ///Donde cambiamos el estado de deleted en la clase local?
        ///aqui, o antes de entrar en este metodo cuando aun tenemos el objeto en si
        ///i no solo el Id.

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
        StringBuilder query = new StringBuilder("UPDATE escaperomdb." + tableName + " SET ");
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
        serialize(queryString, enumAttributes, dbConnection, "set");
    }


    @Override
    public ArrayList<Entity> getAll(EntityAttributes enumAttributes) {

        return null;
    }

}
