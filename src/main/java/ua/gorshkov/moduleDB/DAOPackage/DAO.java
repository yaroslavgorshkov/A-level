package ua.gorshkov.moduleDB.DAOPackage;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    T save (T obj);
    T update (T obj);
    void delete (T obj);
    Optional<T> get (Long id);
    List<T> getAll();
}
