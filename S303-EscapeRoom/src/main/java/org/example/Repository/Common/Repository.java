package org.example.Repository.Common;

import java.util.ArrayList;

public interface Repository<T> {

    void add(T entity, EntityAttributes enumAttributes);
    T getById(int id);
    void delete(int id);
    void update(T entity);
    ArrayList<T> getAll();
}