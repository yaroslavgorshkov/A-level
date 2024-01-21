package ua.gorshkov.moduleDB.DBManagersPackage;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ua.gorshkov.moduleDB.DAOPackage.DAO;
import ua.gorshkov.moduleDB.DAOPackage.HibernateDAO.DBAccountHibernateDAO;
import ua.gorshkov.moduleDB.Entities.Account;
import ua.gorshkov.moduleDB.Entities.Operation;
import ua.gorshkov.moduleDB.Entities.User;

import java.util.List;
import java.util.Optional;

public class AccountDBManager {
    private static final DAO<Account> accountDAO = new DBAccountHibernateDAO();
    public static Account save(Account account) {
        return accountDAO.save(account);
    }
    public static Account update(Account account) {
        return accountDAO.update(account);
    }
    public static void delete(User user, Account account) {
        accountDAO.delete(account);
        UserDBManager.updateAccountListOnSpecificUser(user);
    }
    public static void delete(Account account) {
        accountDAO.delete(account);
    }
    public static Optional<Account> get(Long id) {
        return accountDAO.get(id);
    }
    public static List<Account> getAll() {
        return accountDAO.getAll();
    }

    public static void addOperation(Account account, Operation operation){
        operation.setAccount(account);
        OperationDBManager.save(operation);
        updateOperationListOnSpecificAccount(account);
    }

    public static void updateOperationListOnSpecificAccount (Account account){
        String currentAccountOperationListQuery = "FROM Operation o WHERE o.account = :currentAccount";
        try (EntityManager entityManager = ConnectionManager.getEntityManager()) {
            try {
                entityManager.getTransaction().begin();
                List<Operation> operationList = entityManager.createQuery(currentAccountOperationListQuery, Operation.class)
                        .setParameter("currentAccount", account)
                        .getResultList();
                account.getOperationList().clear();
                for (Operation operation : operationList){
                    account.getOperationList().add(operation);
                }
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }
}

