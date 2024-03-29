package ua.gorshkov.moduleDB.DAOPackage.HibernateDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ua.gorshkov.moduleDB.DAOPackage.DAO;
import ua.gorshkov.moduleDB.DBManagersPackage.AccountDBManager;
import ua.gorshkov.moduleDB.DBManagersPackage.ConnectionManager;
import ua.gorshkov.moduleDB.DBManagersPackage.OperationDBManager;
import ua.gorshkov.moduleDB.Entities.Account;
import ua.gorshkov.moduleDB.Entities.Operation;
import ua.gorshkov.moduleDB.Entities.User;

import java.util.List;
import java.util.Optional;

public class DBUserHibernateDAO implements DAO<User> {
    @Override
    public User save(User obj) {
        try (EntityManager entityManager = ConnectionManager.getEntityManager()) {
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
        try (EntityManager entityManager = ConnectionManager.getEntityManager()) {
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
        try (EntityManager entityManager = ConnectionManager.getEntityManager()) {
            try {
                entityManager.getTransaction().begin();
                int sizeOfAccountList = obj.getAccountList().size();
                for (int  i = 0; i < sizeOfAccountList; i++) {
                    Account deletingAccount = obj.getAccountList().get(0);
                    AccountDBManager.delete(obj, deletingAccount);
                }
                String hql = "DELETE FROM User WHERE id = :entityId";
                entityManager.createQuery(hql)
                        .setParameter("entityId", obj.getId())
                        .executeUpdate();
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
        try (EntityManager entityManager = ConnectionManager.getEntityManager()) {
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
        try (EntityManager entityManager = ConnectionManager.getEntityManager()) {
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
