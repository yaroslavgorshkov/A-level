package ua.gorshkov.hw19;
import org.hibernate.Session;
import java.util.List;

public class DBPersonDAO implements DAO<Person> {
    @Override
    public Person save(Person obj) {
        try(Session session = MySessionManager.getSession()) {
            session.beginTransaction();
            session.save(obj);
            try {
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
        try(Session session = MySessionManager.getSession()) {
            session.beginTransaction();
            session.update(obj);
            try {
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
        try(Session session = MySessionManager.getSession()) {
            session.beginTransaction();
            session.delete(obj);
            try {
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Person get(Long id) {
        Person obj;
        try(Session session = MySessionManager.getSession()) {
            session.beginTransaction();
            obj = session.get(Person.class, id);
            try {
                session.getTransaction().commit();
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
        try(Session session = MySessionManager.getSession()) {
            session.beginTransaction();
            personList = session.createQuery(selectAllQuery, Person.class).getResultList();
            try {
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
        return personList;
    }
}
