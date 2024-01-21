package ua.gorshkov.moduleDB.DBManagersPackage;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ua.gorshkov.moduleDB.DAOPackage.DAO;
import ua.gorshkov.moduleDB.DAOPackage.HibernateDAO.DBUserHibernateDAO;
import ua.gorshkov.moduleDB.Entities.Account;
import ua.gorshkov.moduleDB.Entities.User;

import java.util.List;
import java.util.Optional;

public class UserDBManager {
    private static final DAO<User> userDAO = new DBUserHibernateDAO();
    public static User save(User user) {
        return userDAO.save(user);
    }
    public static User update(User user) {
        return userDAO.update(user);
    }
    public static void delete(User user) {
        userDAO.delete(user);
    }
    public static Optional<User> get(Long id) {
        return userDAO.get(id);
    }
    public static List<User> getAll() {
        return userDAO.getAll();
    }

    public static void addAccount(User user, Account account){
        account.setUser(user);
        AccountDBManager.save(account);
        updateAccountListOnSpecificUser(user);
    }

    public static void updateAccountListOnSpecificUser (User user){
        String currentUserAccountListQuery = "FROM Account a WHERE a.user = :currentUser";
        try(EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
            EntityManager entityManager = entityManagerFactory.createEntityManager()
        ) {
            try {
                entityManager.getTransaction().begin();
                List<Account> accountList = entityManager.createQuery(currentUserAccountListQuery, Account.class)
                        .setParameter("currentUser", user)
                        .getResultList();
                user.getAccountList().clear();
                for (Account account : accountList){
                    user.getAccountList().add(account);
                }
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }
}
