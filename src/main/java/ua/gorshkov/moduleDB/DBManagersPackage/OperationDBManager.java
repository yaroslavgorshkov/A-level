package ua.gorshkov.moduleDB.DBManagersPackage;

import ua.gorshkov.moduleDB.DAOPackage.DAO;
import ua.gorshkov.moduleDB.DAOPackage.HibernateDAO.DBOperationHibernateDAO;
import ua.gorshkov.moduleDB.Entities.Account;
import ua.gorshkov.moduleDB.Entities.Operation;

import java.util.List;
import java.util.Optional;

public class OperationDBManager {
    private static final DAO<Operation> operationDAO = new DBOperationHibernateDAO();

    public static Operation save(Operation operation) {
        return operationDAO.save(operation);
    }

    public static Operation update(Operation operation) {
        return operationDAO.update(operation);
    }

    public static void delete(Account account, Operation operation) {
        operationDAO.delete(operation);
        AccountDBManager.updateOperationListOnSpecificAccount(account);
    }

    public static void delete(Operation operation) {
        operationDAO.delete(operation);
    }

    public static Optional<Operation> get(Long id) {
        return operationDAO.get(id);
    }
}
