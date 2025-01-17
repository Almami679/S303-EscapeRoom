package org.example.Repository.Common;

import org.example.Modules.Entity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Repository {

    void add(Entity entity, EntityAttributes enumAttributes) throws SQLException;
    Entity getById(int id, EntityAttributes enumAttributes);
    void delete(int id, EntityAttributes enumAttributes);
    void update(Entity entity, EntityAttributes enumAttributes);
    ArrayList<Entity> getAll(EntityAttributes enumAttributes);
}