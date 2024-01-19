package ua.gorshkov.moduleDB;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        BankManager bankManager = new BankManager();
        bankManager.start();
        }
    }
