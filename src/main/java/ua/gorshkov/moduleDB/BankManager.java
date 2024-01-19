package ua.gorshkov.moduleDB;

import ua.gorshkov.moduleDB.DBManagersPackage.AccountDBManager;
import ua.gorshkov.moduleDB.DBManagersPackage.OperationDBManager;
import ua.gorshkov.moduleDB.DBManagersPackage.UserDBManager;
import ua.gorshkov.moduleDB.Entities.Account;
import ua.gorshkov.moduleDB.Entities.Operation;
import ua.gorshkov.moduleDB.Entities.User;
import ua.gorshkov.moduleDB.Validation.OperationsValidator;
import ua.gorshkov.moduleDB.Validation.SetterClass;
import ua.gorshkov.moduleDB.Validation.StringValidationStrategies;

import java.util.Scanner;

public class BankManager {
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        this.menu();
    }

    private void menu() {
        System.out.println("Welcome to the Bank Manager menu!");
        System.out.println("Please, register with your personal information.");
        System.out.println("Adding the user:");
        User user = new User();
        user.setNameByOwn();
        user.setSurnameByOwn();
        UserDBManager.save(user);
        User currentUser = user;
        Account currentAccount = null;
        System.out.println("User was successfully added!");
        while (true) {
            if (currentUser.getAccountList().isEmpty()) {
                System.out.println("What do you want to do, " + currentUser.getName() + " " +
                        currentUser.getSurname() + " (no accounts)?");
            } else {
                System.out.println("What do you want to do, " + currentUser.getName() + " " +
                        currentUser.getSurname() + " (" + currentAccount.getBankCardNumber() + ")?");
            }

            System.out.println("Add new user - 'add user'");
            System.out.println("Update current user information - 'update user'");
            if (UserDBManager.getAll().size() > 1) {
                System.out.println("Change user - 'change user'");
                System.out.println("Delete user - 'delete user'");
            }
            System.out.println("Add new account to current user - 'add account'");
            if (!currentUser.getAccountList().isEmpty()) {
                System.out.println("Update account info - 'update account'");
                if (currentUser.getAccountList().size() > 1) {
                    System.out.println("Change account - 'change account'");
                }
                System.out.println("Delete current account - 'delete account'");
                System.out.println("Add operation to the current user`s account - 'add operation'");
                if (!currentAccount.getOperationList().isEmpty()) {
                    System.out.println("Update last operation info - 'update last operation'");
                    System.out.println("Delete last operation info - 'delete last operation'");
                    System.out.println("Delete all operations info - 'delete all operations'");
                }
            }
            if (!OperationDBManager.getAll().isEmpty()) {
                System.out.println("Get quick information about users, accounts and operations - 'quick info'");
                System.out.println("Get an \"from and to\" account extract in csv. format - 'csv extract'");
            }
            System.out.println("Finish the program - 'finish'");
            System.out.print("Your operation - ");
            String operation = scanner.nextLine();
            if (checkingWhetherTheOperationCanBePerformed(operation, currentUser, currentAccount)) {
                if (operation.equals("add user")) {
                    User newUser = new User();
                    newUser.setNameByOwn();
                    newUser.setSurnameByOwn();
                    currentUser = newUser;
                    UserDBManager.save(newUser);
                } else if (operation.equals("update user")) {
                    currentUser.setNameByOwn();
                    currentUser.setSurnameByOwn();
                    UserDBManager.update(currentUser);
                } else if (operation.equals("change user")) { ///???
                    currentUser = this.changeUser();
                    currentAccount = currentUser.getAccountList().get(0);
                } else if (operation.equals("delete user")) {
                    UserDBManager.delete(currentUser);
                    currentUser = UserDBManager.getAll().get(0);
                } else if (operation.equals("add account")) {
                    Account newAccount = new Account();
                    newAccount.setBankCardNumberByOwn();
                    newAccount.setStartMoneyAmountByOwn();
                    currentAccount = newAccount;
                    UserDBManager.addAccount(currentUser, newAccount);
                } else if (operation.equals("update account")) {
                    currentAccount.setBankCardNumberByOwn();
                    currentAccount.setStartMoneyAmountByOwn();
                    AccountDBManager.update(currentAccount);
                } else if (operation.equals("change account")) { ///???
                    currentAccount = this.changeAccount(currentUser);
                } else if (operation.equals("delete account")) {
                    AccountDBManager.delete(currentUser, currentAccount);
                    if (currentUser.getAccountList().isEmpty()) {
                        currentAccount = null;
                    } else {
                        currentAccount = currentUser.getAccountList().get(0);
                    }
                } else if (operation.equals("add operation")) {
                    Operation newOperation = new Operation();
                    newOperation.setCategoryByOwn();
                    newOperation.setOperationCategoryByOwn();
                    newOperation.setAmountOfMoneyByOwn();
                    if (OperationsValidator.isOperationValid(currentAccount, newOperation)) {
                        AccountDBManager.addOperation(currentAccount, newOperation);
                        OperationsValidator.displayChangesOnAccount(currentAccount, newOperation);
                    }
                } else if (operation.equals("update last operation")) {
                    Operation lastOperation = currentAccount.getOperationList().getLast();
                    lastOperation.setCategoryByOwn();
                    lastOperation.setOperationCategoryByOwn();
                    lastOperation.setAmountOfMoneyByOwn();
                    OperationDBManager.update(lastOperation);
                } else if (operation.equals("delete last operation")) {
                    OperationDBManager.delete(currentAccount, currentAccount.getOperationList().getLast());
                } else if (operation.equals("delete all operations")) {
                    int currentOperationListSize = currentAccount.getOperationList().size();
                    for (int i = 0; i < currentOperationListSize; i++) {
                        Operation deletingOperation = currentAccount.getOperationList().get(0);
                        OperationDBManager.delete(currentAccount, deletingOperation);
                    }
                } else if (operation.equals("quick info")) {
                    /// ...
                } else if (operation.equals("csv extract")) {
                    /// ...
                } else if (operation.equals("finish")) {
                    return;
                } else {
                    System.out.println("Unknown operation! Try one more time!");
                }
            } else {
                System.out.println("There is no such operation in the selection of operations!");
            }
            System.out.println('\n');
        }
    }

    private User changeUser() {
        while (true) {
            try {
                System.out.print("Enter the first and last name with a space for changing the user.");
                System.out.print("Enter the name: ");
                String name = SetterClass.setString("User name = ",
                        StringValidationStrategies.ALPHABETIC_ONLY,
                        StringValidationStrategies.CAPITALIZATION,
                        StringValidationStrategies.WITHOUT_PASSES,
                        StringValidationStrategies.NOT_EMPTY);

                System.out.print("Enter the surname: ");
                String surname = SetterClass.setString("User surname = ",
                        StringValidationStrategies.ALPHABETIC_ONLY,
                        StringValidationStrategies.CAPITALIZATION,
                        StringValidationStrategies.WITHOUT_PASSES,
                        StringValidationStrategies.NOT_EMPTY);

                for (User user : UserDBManager.getAll()) {
                    if (user.getName().equals(name) && user.getSurname().equals(surname)) {
                        return UserDBManager.get(user.getId()).get();
                    }
                }
                throw new RuntimeException("There are no users with such data in the database!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Account changeAccount(User user) {
        while (true) {
            try {
                System.out.print("Enter the Bank Card Number to change your account: ");
                String cardNumber = SetterClass.setString("Bank card number = ",
                        StringValidationStrategies.NUMERIC_ONLY,
                        StringValidationStrategies.WITHOUT_PASSES,
                        StringValidationStrategies.NOT_EMPTY);
                for (Account account : user.getAccountList()) {
                    if (account.getBankCardNumber().equals(cardNumber)) {
                        return AccountDBManager.get(account.getId()).get();
                    }
                }
                throw new RuntimeException("There are no accounts connected to current user with such data in the database!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static boolean checkingWhetherTheOperationCanBePerformed(String operation, User user, Account account) {
        if (operation.equals("change user") && UserDBManager.getAll().size() == 1) {
            return false;
        } else if (operation.equals("delete user") && UserDBManager.getAll().size() == 1) {
            return false;
        } else if (operation.equals("update account") && user.getAccountList().isEmpty()) {
            return false;
        } else if (operation.equals("change account") && user.getAccountList().size() <= 1) {
            return false;
        } else if (operation.equals("delete account") && user.getAccountList().isEmpty()) {
            return false;
        } else if (operation.equals("add operation") && user.getAccountList().isEmpty()) {
            return false;
        } else if (operation.equals("update last operation") && account.getOperationList().isEmpty()
                && user.getAccountList().isEmpty()) {
            return false;
        } else if (operation.equals("delete last operation") && account.getOperationList().isEmpty()
                && user.getAccountList().isEmpty()) {
            return false;
        } else if (operation.equals("delete all operations") && account.getOperationList().isEmpty()
                && user.getAccountList().isEmpty()) {
            return false;
        } else if (operation.equals("quick info") && OperationDBManager.getAll().isEmpty()) {
            return false;
        } else if (operation.equals("csv extract") && OperationDBManager.getAll().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
