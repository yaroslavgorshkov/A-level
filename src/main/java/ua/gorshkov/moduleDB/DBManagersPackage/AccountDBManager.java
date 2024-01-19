package ua.gorshkov.moduleDB.DBManagersPackage;

import ua.gorshkov.moduleDB.DAOPackage.DAO;
import ua.gorshkov.moduleDB.DAOPackage.DBAccountHibernateDAO;
import ua.gorshkov.moduleDB.DAOPackage.DBUserHibernateDAO;
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
        account.getOperationList().add(operation);
    }
}

