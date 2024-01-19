package ua.gorshkov.moduleDB;

import ua.gorshkov.moduleDB.DBManagersPackage.UserDBManager;

public class Main {
    public static void main(String[] args) {
        BankManager bankManager = new BankManager();
        bankManager.start();
    }
}
