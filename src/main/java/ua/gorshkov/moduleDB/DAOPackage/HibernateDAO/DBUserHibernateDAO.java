package ua.gorshkov.moduleDB.DAOPackage.HibernateDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ua.gorshkov.moduleDB.DAOPackage.DAO;
import ua.gorshkov.moduleDB.DBManagersPackage.ConnectionManager;
import ua.gorshkov.moduleDB.Entities.User;

import java.util.List;
import java.util.Optional;

public class DBUserHibernateDAO implements DAO<User> {
    @Override
    public User save(User obj) {
        try(EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager()
        ) {
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(obj);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
        return obj;
    }

    @Override
    public User update(User obj) {
        try(EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
            EntityManager entityManager = entityManagerFactory.createEntityManager()
        ) {
            try {
                entityManager.getTransaction().begin();
                entityManager.merge(obj);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
        return obj;
    }

    @Override
    public void delete(User obj) {
        try(EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
            EntityManager entityManager = entityManagerFactory.createEntityManager()
        ) {
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(entityManager.contains(obj) ? obj : entityManager.merge(obj));
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Optional<User> get(Long id) {
        Optional<User> obj;
        try(EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
            EntityManager entityManager = entityManagerFactory.createEntityManager()
        ) {
            try {
                entityManager.getTransaction().begin();
                User user = entityManager.find(User.class, id);
                entityManager.getTransaction().commit();
                obj = Optional.ofNullable(user);
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
        return obj;
    }

    @Override
    public List<User> getAll() {
        List<User> userList;
        String selectAllQuery = "select u from User u";
        try(EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
            EntityManager entityManager = entityManagerFactory.createEntityManager()
        ) {
            try {
                entityManager.getTransaction().begin();
                userList = entityManager.createQuery(selectAllQuery, User.class).getResultList();
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
        return userList;
    }
}
