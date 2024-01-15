package ua.gorshkov.hw19;
import org.hibernate.Session;
import java.util.List;
import java.util.Optional;

public class DBPersonHibernateDAO implements DAO<Person> {
    @Override
    public Person save(Person obj) {
        try(Session session = ConnectionsManager.getSession()) {
            try {
                session.beginTransaction();
                session.save(obj);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
        return obj;
    }

    @Override
    public Person update(Person obj) {
        try(Session session = ConnectionsManager.getSession()) {
            try {
                session.beginTransaction();
                session.update(obj);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
        return obj;
    }

    @Override
    public void delete(Person obj) {
        try(Session session = ConnectionsManager.getSession()) {
            try {
                session.beginTransaction();
                session.delete(obj);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Optional<Person> get(Long id) {
        Optional<Person> obj;
        try (Session session = ConnectionsManager.getSession()) {
            try {
                session.beginTransaction();
                Person person = session.get(Person.class, id);
                session.getTransaction().commit();
                obj = Optional.ofNullable(person);
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
        return obj;
    }
    @Override
    public List<Person> getAll() {
        List<Person> personList;
        String selectAllQuery = "select p from Person p";
        try(Session session = ConnectionsManager.getSession()) {
            try {
                session.beginTransaction();
                personList = session.createQuery(selectAllQuery, Person.class).getResultList();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
        return personList;
    }
}
