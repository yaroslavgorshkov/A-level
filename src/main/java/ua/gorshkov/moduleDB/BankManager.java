package ua.gorshkov.moduleDB;

import com.opencsv.CSVWriter;
import ua.gorshkov.moduleDB.DBManagersPackage.AccountDBManager;
import ua.gorshkov.moduleDB.DBManagersPackage.OperationDBManager;
import ua.gorshkov.moduleDB.DBManagersPackage.UserDBManager;
import ua.gorshkov.moduleDB.Entities.Account;
import ua.gorshkov.moduleDB.Entities.Enums.OperationCategories;
import ua.gorshkov.moduleDB.Entities.Operation;
import ua.gorshkov.moduleDB.Entities.User;
import ua.gorshkov.moduleDB.Validation.OperationsValidator;
import ua.gorshkov.moduleDB.Validation.SetterClass;
import ua.gorshkov.moduleDB.Validation.StringValidationStrategies;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class BankManager {
    private final Scanner scanner = new Scanner(System.in);
    private int csvAccountFileNumber = 0;
    private int csvUserFileNumber = 0;

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
            showMenuOperations(currentUser, currentAccount);
            System.out.print("Your operation - ");
            String operation = scanner.nextLine();

            if (checkingWhetherTheOperationCanBePerformed(operation, currentUser, currentAccount)) {
                if (operation.equals("add user")) { ///!!!
                    currentUser = addUserMenuOperation(currentUser);
                } else if (operation.equals("update user")) {
                    updateUserMenuOperation(currentUser);
                } else if (operation.equals("change user")) {
                    currentUser = changeUser();
                    if (!currentUser.getAccountList().isEmpty()) {
                        currentAccount = currentUser.getAccountList().get(0);
                    } else {
                        currentAccount = null;
                    }
                } else if (operation.equals("delete user")) {
                    UserDBManager.delete(UserDBManager.get(currentUser.getId()).get());
                    currentUser = UserDBManager.getAll().get(0);
                    if (currentUser.getAccountList().isEmpty()) {
                        currentAccount = null;
                    } else {
                        currentAccount = currentUser.getAccountList().get(0);
                    }
                } else if (operation.equals("add account")) {
                    currentAccount = addAccountMenuOperation(currentUser, currentAccount);
                } else if (operation.equals("update account")) {
                    updateAccountMenuOperation(currentAccount);
                } else if (operation.equals("change account")) {
                    currentAccount = changeAccount(currentUser);
                } else if (operation.equals("delete account")) {
                    currentAccount = deleteAccountMenuOperation(currentUser, currentAccount);
                } else if (operation.equals("add operation")) {
                    addOperationMenuOperation(currentAccount);
                } else if (operation.equals("update last operation")) {
                    updateLastOperationMenuOperation(currentAccount);
                } else if (operation.equals("delete last operation")) {
                    OperationDBManager.delete(currentAccount, currentAccount.getOperationList().getLast());
                } else if (operation.equals("delete all operations")) {
                    deleteAllOperationsMenuOperation(currentAccount);
                } else if (operation.equals("quick info")) {
                    getQuickInfoAboutAccountOperationsMenuOperation(currentAccount);
                } else if (operation.equals("csv extract")) {
                    makeCSVAccountExtractMenuOperation(currentAccount);
                } else if (operation.equals("get user info")) {
                    getUserInfoMenuOperation(currentUser);
                } else if (operation.equals("sort operations")) {
                    sortOperationsMenuOperation(currentUser);
                } else if (operation.equals("get most operations")) {
                    getBiggestAndSmallestOperationsBySpecificUserMenuOperation(currentUser);
                } else if (operation.equals("filter user operations")) {
                    filterUserOperationsMenuOperation(currentUser);
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

    private void showMenuOperations(User user, Account account) {
        if (user.getAccountList().isEmpty()) {
            System.out.println("What do you want to do, " + user.getName() + " " +
                    user.getSurname() + " (no accounts)?");
        } else {
            System.out.println("What do you want to do, " + user.getName() + " " +
                    user.getSurname() + " (" + account.getBankCardNumber() + ")?");
        }

        System.out.println("Add new user - 'add user'");
        System.out.println("Update current user information - 'update user'");
        if (UserDBManager.getAll().size() > 1) {
            System.out.println("Change user - 'change user'");
            System.out.println("Delete user - 'delete user'");
        }
        System.out.println("Add new account to current user - 'add account'");
        if (!user.getAccountList().isEmpty()) {
            System.out.println("Update account info - 'update account'");
            if (user.getAccountList().size() > 1) {
                System.out.println("Change account - 'change account'");
            }
            System.out.println("Delete current account - 'delete account'");
            System.out.println("Add operation to the current user`s account - 'add operation'");
            if (!account.getOperationList().isEmpty()) {
                System.out.println("Update last operation info - 'update last operation'");
                System.out.println("Delete last operation info - 'delete last operation'");
                System.out.println("Delete all operations info - 'delete all operations'");
            }
        }
        if (account != null) {
            if (!account.getOperationList().isEmpty()) {
                System.out.println("Get quick information about current account - 'quick info'");
                System.out.println("Get an \"from and to\" account extract in csv. format - 'csv extract'");
            }
        }
        if (user.getAccountList().size() > 1) {
            System.out.println("Get info about all user`s accounts: 'get user info'");
        }
        if (isCurrentUserHasOperationsOnAccounts(user)) {
            System.out.println("Get sorted operations list by current user: 'sort operations'");
            System.out.println("Get the 'Most' operations by current user: 'get most operations'");
            System.out.println("Filter user operations by operation category: 'filter user operations'");
        }
        System.out.println("Finish the program - 'finish'");
    }

    private boolean checkingWhetherTheOperationCanBePerformed(String operation, User user, Account account) {
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
        } else if (operation.equals("update last operation")) {
            if (account == null) {
                return false;
            } else {
                return !account.getOperationList().isEmpty();
            }
        } else if (operation.equals("delete last operation")) {
            if (account == null) {
                return false;
            } else {
                return !account.getOperationList().isEmpty();
            }
        } else if (operation.equals("delete all operations")) {
            if (account == null) {
                return false;
            } else {
                return !account.getOperationList().isEmpty();
            }
        } else if (operation.equals("quick info")) {
            if (account == null) {
                return false;
            } else {
                return !account.getOperationList().isEmpty();
            }
        } else if (operation.equals("csv extract")) {
            if (account == null) {
                return false;
            } else {
                return !account.getOperationList().isEmpty();
            }
        } else if (operation.equals("get user info") && user.getAccountList().size() <= 1) {
            return false;
        } else if (operation.equals("sort operations")) {
            if (account == null) {
                return false;
            } else {
                return isCurrentUserHasOperationsOnAccounts(user);
            }
        } else if (operation.equals("get most operations")) {
            if (account == null) {
                return false;
            } else {
                return isCurrentUserHasOperationsOnAccounts(user);
            }
        } else if (operation.equals("filter user operations")) {
            if (account == null) {
                return false;
            } else {
                return isCurrentUserHasOperationsOnAccounts(user);
            }
        } else {
            return true;
        }
    }

    private User addUserMenuOperation(User user) {
        User newUser = new User();
        newUser.setNameByOwn();
        newUser.setSurnameByOwn();
        if (isNewUserIsUnique(newUser)) {
            user = newUser;
            UserDBManager.save(newUser);
        } else {
            System.out.println("There is already a user with this data in the database! " +
                    "Cannot confirm adding the new User!");
        }
        return user;
    }

    private static void updateUserMenuOperation(User user) {
        user.setNameByOwn();
        user.setSurnameByOwn();
        UserDBManager.update(user);
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

    private Account addAccountMenuOperation(User user, Account account) {
        Account newAccount = new Account();
        newAccount.setBankCardNumberByOwn();
        if (whetherThisUserAlreadyHasThisAccount(user, newAccount.getBankCardNumber())) {
            System.out.println("Cannot add new account to current user!");
            System.out.println("You have already registered this account!");
        } else if (whetherThisAccountAlreadyExistsInDatabase(newAccount.getBankCardNumber())) {
            System.out.println("Cannot add new account to current user!");
            System.out.println("This account is registered to another user!");
        } else {
            newAccount.setStartMoneyAmountByOwn();
            account = newAccount;
            UserDBManager.addAccount(user, newAccount);
        }
        return account;
    }

    private boolean whetherThisAccountAlreadyExistsInDatabase(String number) {
        List<Account> accountList = AccountDBManager.getAll();
        for (Account account : accountList) {
            if (account.getBankCardNumber().equals(number)) {
                return true;
            }
        }
        return false;
    }

    private static void updateAccountMenuOperation(Account account) {
        account.setBankCardNumberByOwn();
        account.setStartMoneyAmountByOwn();
        AccountDBManager.update(account);
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

    private static Account deleteAccountMenuOperation(User user, Account account) {
        AccountDBManager.delete(user, account);
        if (user.getAccountList().isEmpty()) {
            account = null;
        } else {
            account = user.getAccountList().get(0);
        }
        return account;
    }

    private static void addOperationMenuOperation(Account account) {
        Operation newOperation = new Operation();
        newOperation.setCategoryByOwn();
        newOperation.setOperationCategoryByOwn();
        newOperation.setAmountOfMoneyByOwn();
        if (OperationsValidator.isOperationValid(account, newOperation)) {
            AccountDBManager.addOperation(account, newOperation);
            OperationsValidator.displayChangesOnAccount(account, newOperation);
        }
    }

    private static void updateLastOperationMenuOperation(Account account) {
        Operation lastOperation = account.getOperationList().getLast();
        lastOperation.setCategoryByOwn();
        OperationDBManager.update(lastOperation);
    }

    private static void deleteAllOperationsMenuOperation(Account account) {
        int currentOperationListSize = account.getOperationList().size();
        for (int i = 0; i < currentOperationListSize; i++) {
            Operation deletingOperation = account.getOperationList().get(0);
            OperationDBManager.delete(account, deletingOperation);
        }
    }

    private void getQuickInfoAboutAccountOperationsMenuOperation(Account account) {
        System.out.println("Info about " + account.getBankCardNumber() + " account (\u001B[31m"
                + account.getStartMoneyAmount() + "\u001B[0m):");
        System.out.println("Operations:");
        for (Operation operation : account.getOperationList().reversed()) {
            if (operation.getOperationCategory() == OperationCategories.EXPENSE) {
                System.out.println("\u001B[31mOperation category: " + operation.getCategory() + " , -" +
                        operation.getAmountOfMoney() + "\u001B[0m");
            } else {
                System.out.println("\u001B[32mOperation category: " + operation.getCategory() + " , +" +
                        operation.getAmountOfMoney() + "\u001B[0m");
            }
        }
        System.out.println();
    }

    private void makeCSVAccountExtractMenuOperation(Account account) {
        while (true) {
            try {
                System.out.println("Specify the \"from\" and \"to\" time frame in which you want to get the list of operations of a particular user.");
                System.out.println("Example of time format: \"2023-1-20-13\"");
                System.out.println("Where 2023 - year, 1 - month, 20 - date, 13 - time.");
                System.out.print("From: ");
                String dateFrom = scanner.nextLine();
                System.out.print("To: ");
                String dateTo = scanner.nextLine();
                makeCSVAccountExtractMenuOperation(account, dateFrom, dateTo);
                break;
            } catch (Exception e) {
                System.out.println("Incorrect date format, try entering again!");
            }
        }
    }

    private void makeCSVAccountExtractMenuOperation(Account account, String from, String to) throws DateTimeParseException {
        String csvFilePath = "account_extract" + csvAccountFileNumber + ".csv";
        this.csvAccountFileNumber++;
        List<Operation> resultOperationList = new ArrayList<>();
        for (Operation operation : account.getOperationList()) {
            if (convertDateToInteger(operation.getCreationTime()) >
                    convertDateToInteger(from) &&
                    convertDateToInteger(operation.getCreationTime()) <
                            convertDateToInteger(to)) {
                resultOperationList.add(operation);
            }
        }
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath))) {
            String[] headerAccount = {"Account: "
                    , "Card number = " + account.getBankCardNumber()
                    , "Amount of money = " + account.getStartMoneyAmount()};
            writer.writeNext(headerAccount);
            String[] headerOperationFields = {"Category"
                    , "Amount of money"
                    , "EXPENSE / INCOME"};
            writer.writeNext(headerOperationFields);
            for (Operation operation : resultOperationList) {
                String[] operationFields = new String[3];
                operationFields[0] = operation.getCategory();
                operationFields[1] = operation.getAmountOfMoney().toString();
                operationFields[2] = operation.getOperationCategory().toString();
                writer.writeNext(operationFields);
            }
            System.out.println("Data was successfully added to account_extract.csv file! Name of file: " + csvFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getUserInfoMenuOperation(User user) {
        System.out.println("How do u want to get user info extract?");
        System.out.println("1 - in .csv format");
        System.out.println("2 - into the console");
        while (true) {
            try {
                int extractFormatNumber = scanner.nextInt();
                if (extractFormatNumber == 1) {
                    makeCSVUserExtract(user);
                } else if (extractFormatNumber == 2) {
                    makeConsoleUserExtract(user);
                } else {
                    throw new RuntimeException("There are no such choice! try one more time.");
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void makeCSVUserExtract(User user) {
        String csvUserFilePath = "user_extract" + csvUserFileNumber + ".csv";
        csvUserFileNumber++;
        double totalCash = 0;

        try (CSVWriter writer = new CSVWriter(new FileWriter(csvUserFilePath))) {
            String[] headerAccount = {"User: "
                    , "Name = " + user.getName()
                    , "Surname = " + user.getSurname()};
            writer.writeNext(headerAccount);
            for (Account account : user.getAccountList()) {
                Optional<Account> getAccount = AccountDBManager.get(account.getId());
                totalCash += getAccount.get().getStartMoneyAmount();
                String[] accountFields = new String[3];
                accountFields[0] = "Account:";
                accountFields[1] = "Number = " + getAccount.get().getBankCardNumber();
                accountFields[2] = "Money Amount = " + getAccount.get().getStartMoneyAmount();
                writer.writeNext(accountFields);
            }
            String[] summarizing = new String[2];
            summarizing[0] = "SUMMA = ";
            summarizing[1] = String.valueOf(totalCash);
            writer.writeNext(summarizing);

            System.out.println("Data was successfully added to user_extract.csv file! Name of file:" + csvUserFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void makeConsoleUserExtract(User user) {
        double totalCash = 0;
        System.out.println("User: " + user.getName() + " " + user.getSurname());
        System.out.println("Accounts:");
        for (Account account : user.getAccountList()) {
            Optional<Account> getAccount = AccountDBManager.get(account.getId());
            totalCash += getAccount.get().getStartMoneyAmount();
            System.out.println("Number: " +
                    getAccount.get().getBankCardNumber() + ", amount of money: " + getAccount.get().getStartMoneyAmount());
        }
        System.out.println("\u001B[32mTotal cash sum = " + totalCash + "\u001B[0m");
    }

    private void sortOperationsMenuOperation(User user) {
        List<Operation> resultList;
        while (true) {
            try {
                System.out.println("What type of sorting you want to choose: 1 - ascending, 2 - descending");
                System.out.print("Your choose: ");
                int chooseTypeOfSorting = scanner.nextInt();
                if (chooseTypeOfSorting == 1) {
                    resultList = getSortedOperationsAscending(user);
                } else if (chooseTypeOfSorting == 2) {
                    resultList = getSortedOperationsDescending(user);
                } else {
                    throw new RuntimeException();
                }
                System.out.println("Result operations order:");
                resultList.forEach(operation ->
                        System.out.println("Amount of money: " + operation.getAmountOfMoney()
                                + ", Category: " + operation.getCategory()
                                + ", type of transaction: " + operation.getOperationCategory().toString()
                                + ", account: " + operation.getAccount().getBankCardNumber()));
                break;
            } catch (Exception e) {
                System.out.println("Incorrect choose of type of sorting! Try one more time.");
            }
        }
    }

    private List<Operation> getSortedOperationsAscending(User user) {
        List<Operation> operationListOfCurrentUser = getAllOperationsListBySpecificUser(user);
        return operationListOfCurrentUser.stream()
                .sorted(Comparator.comparingDouble(Operation::getAmountOfMoney))
                .collect(Collectors.toList());
    }

    private List<Operation> getSortedOperationsDescending(User user) {
        List<Operation> operationListOfCurrentUser = getAllOperationsListBySpecificUser(user);
        return operationListOfCurrentUser.stream()
                .sorted(Comparator.comparingDouble(Operation::getAmountOfMoney).reversed())
                .collect(Collectors.toList());
    }

    private void getBiggestAndSmallestOperationsBySpecificUserMenuOperation(User user) {
        List<Operation> operationListOfSpecificUser = getAllOperationsListBySpecificUser(user);
        Optional<Operation> biggestOperation = operationListOfSpecificUser.stream()
                .max(Comparator.comparing(Operation::getAmountOfMoney));
        Optional<Operation> smallestOperation = operationListOfSpecificUser.stream()
                .min(Comparator.comparing(Operation::getAmountOfMoney));
        System.out.println("The 'Most' operations by current user:");
        System.out.println("Biggest operation: " + biggestOperation.get().getCategory() +
                ", " + biggestOperation.get().getAmountOfMoney() + " ("
                + biggestOperation.get().getOperationCategory().toString() + "). Card: "
                + biggestOperation.get().getAccount().getBankCardNumber());
        System.out.println("Smallest operation: " + smallestOperation.get().getCategory() +
                ", " + smallestOperation.get().getAmountOfMoney() + " ("
                + smallestOperation.get().getOperationCategory().toString() + "). Card: "
                + smallestOperation.get().getAccount().getBankCardNumber());
    }

    private void filterUserOperationsMenuOperation(User user) {
        while (true) {
            try {
                System.out.println("What type of filter operation you want to choose: 1 - only EXPENSE, 2 - only INCOME");
                System.out.print("Your choose: ");
                String input = scanner.nextLine();
                int chooseTypeOfFiltering = Integer.parseInt(input);

                if (chooseTypeOfFiltering < 1 || chooseTypeOfFiltering > 2) {
                    throw new RuntimeException();
                }

                filterUserOperationsMenuOperation(chooseTypeOfFiltering, user);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Incorrect choose of type of filter! Try one more time.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void filterUserOperationsMenuOperation(int choose, User user) {
        List<Operation> operationList = getAllOperationsListBySpecificUser(user);
        List<Operation> resultList;
        if (choose == 1) {
            resultList = operationList.stream()
                    .filter(o -> o.getOperationCategory() == OperationCategories.EXPENSE)
                    .collect(Collectors.toList());
        } else {
            resultList = operationList.stream()
                    .filter(o -> o.getOperationCategory() == OperationCategories.INCOME)
                    .collect(Collectors.toList());
        }
        System.out.println("Filtered data:");
        if (resultList.isEmpty()) {
            System.out.println("No data");
            return;
        }
        for (Operation operation : resultList) {
            System.out.println("Category: " + operation.getCategory() + ", Amount of money: "
                    + operation.getAmountOfMoney() + ", Card: "
                    + operation.getAccount().getBankCardNumber());
        }
    }

    private int convertDateToInteger(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d-H");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        long secondsSinceEpoch = dateTime.toEpochSecond(java.time.OffsetDateTime.now().getOffset());
        return Math.toIntExact(secondsSinceEpoch);
    }

    private boolean isNewUserIsUnique(User newUser) {
        List<User> userList = UserDBManager.getAll();
        for (User user : userList) {
            if (user.getName().equals(newUser.getName()) && user.getSurname().equals(newUser.getSurname())) {
                return false;
            }
        }
        return true;
    }

    private boolean whetherThisUserAlreadyHasThisAccount(User user, String number) {
        List<Account> accountListBySpecialUser = user.getAccountList();
        for (Account account : accountListBySpecialUser) {
            if (account.getBankCardNumber().equals(number)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCurrentUserHasOperationsOnAccounts(User user) {
        List<Operation> operationList = getAllOperationsListBySpecificUser(user);
        return !operationList.isEmpty();
    }

    private List<Operation> getAllOperationsListBySpecificUser(User user) {
        List<Operation> operationListOfSpecificUser = new ArrayList<>();
        List<Account> accountList = user.getAccountList();
        for (Account account : accountList) {
            Optional<Account> getAccountOptional = AccountDBManager.get(account.getId());
            if (!getAccountOptional.get().getOperationList().isEmpty()) {
                operationListOfSpecificUser.addAll(getAccountOptional.get().getOperationList());
            }
        }
        return operationListOfSpecificUser;
    }
}