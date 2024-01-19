package ua.gorshkov.moduleDB.DBManagersPackage;

import ua.gorshkov.moduleDB.DAOPackage.DAO;
import ua.gorshkov.moduleDB.DAOPackage.DBUserHibernateDAO;
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
        user.getAccountList().add(account);
    }
}
