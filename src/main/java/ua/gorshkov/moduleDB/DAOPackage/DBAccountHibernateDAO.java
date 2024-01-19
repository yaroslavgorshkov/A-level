package ua.gorshkov.moduleDB.DAOPackage;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ua.gorshkov.moduleDB.Entities.Account;
import ua.gorshkov.moduleDB.DBManagersPackage.ConnectionManager;
import ua.gorshkov.moduleDB.Entities.User;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class DBAccountHibernateDAO implements DAO<Account>{

    @Override
    public Account save(Account obj) {
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
    public Account update(Account obj) {
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
    public void delete(Account obj) {
        try (Connection connection =
                     PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM account WHERE id = ?")) {

            // Устанавливаем параметры запроса
            preparedStatement.setLong(1, obj.getId()); // Предполагаем, что у сущности есть метод getId()

            // Выполняем запрос на удаление
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // Обрабатываем исключение
            throw new RuntimeException("Error deleting account", e);
        }
    }

    @Override
    public Optional<Account> get(Long id) {
        Optional<Account> obj;
        try(EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
            EntityManager entityManager = entityManagerFactory.createEntityManager()
        ) {
            try {
                entityManager.getTransaction().begin();
                Account account = entityManager.find(Account.class, id);
                entityManager.getTransaction().commit();
                obj = Optional.ofNullable(account);
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
        return obj;
    }

    @Override
    public List<Account> getAll() {
        List<Account> accountList;
        String selectAllQuery = "select a from Account a";
        try(EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
            EntityManager entityManager = entityManagerFactory.createEntityManager()
        ) {
            try {
                entityManager.getTransaction().begin();
                accountList = entityManager.createQuery(selectAllQuery, Account.class).getResultList();
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
        return accountList;
    }
}
