package ua.gorshkov.hw19;

import java.util.List;
import java.util.Optional;

public class PersonDBManager {
    private final DAO<Person> personDAO = new DBPersonJDBCDAO();
    public Person save(Person person) {
        return personDAO.save(person);
    }
    public Person update(Person person) {
        return personDAO.update(person);
    }
    public void delete(Person person) {
        personDAO.delete(person);
    }
    public Optional<Person> get(Long id) {
        return personDAO.get(id);
    }
    public List<Person> getAll() {
        return personDAO.getAll();
    }
}
