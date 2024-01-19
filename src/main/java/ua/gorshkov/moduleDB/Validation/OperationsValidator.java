package ua.gorshkov.moduleDB.Validation;

import ua.gorshkov.moduleDB.DBManagersPackage.AccountDBManager;
import ua.gorshkov.moduleDB.Entities.Account;
import ua.gorshkov.moduleDB.Entities.Enums.OperationCategories;
import ua.gorshkov.moduleDB.Entities.Operation;

public class OperationsValidator {
    public static boolean isOperationValid(Account account, Operation operation) {
        if (operation.getOperationCategory() == OperationCategories.EXPENSE && (account.getStartMoneyAmount() - operation.getAmountOfMoney() < 0)) {
            System.out.println("Insufficient funds for the operation! Cannot confirm the transaction!");
            return false;
        } else {
            return true;
        }
    }

    public static void displayChangesOnAccount(Account account, Operation operation) {
        if (operation.getOperationCategory() == OperationCategories.EXPENSE) {
            account.setStartMoneyAmount(account.getStartMoneyAmount() - operation.getAmountOfMoney());
        } else {
            account.setStartMoneyAmount(account.getStartMoneyAmount() + operation.getAmountOfMoney());
        }
        AccountDBManager.update(account);
    }
}
