package com.lessonSpringMVC.dao;

import java.util.List;

public interface Dao <T> {
    Object get(String id);

    List<T> getAll();

    void save(T t);

    void update(T t, String id);

    void delete(String id);
}
