package ua.gorshkov.hw19;

import java.util.List;

public interface DAO<T> {
    T save (T obj);
    T update (T obj);
    void delete (T obj);
    T get (Long id);
    List<T> getAll();
}
