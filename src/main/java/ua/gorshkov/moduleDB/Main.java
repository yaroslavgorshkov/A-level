package ua.gorshkov.moduleDB;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        try(EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("my-persistence-unit");
                EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();

            User user1 = new User();
            user1.setName();
            user1.setSurname();

            Account account1 = new Account();
            account1.setBankCardNumber();
            account1.setStartMoneyAmount();
            account1.setUser(user1);

            Account account2 = new Account();
            account2.setBankCardNumber();
            account2.setStartMoneyAmount();
            account2.setUser(user1);

            Operation operation1 = new Operation();
            operation1.setOperationCategory(OperationCategories.INCOME);
            operation1.setAmountOfMoney(10000.0);
            operation1.setCategory("Stipendia");
            operation1.setAccount(account1);

            Operation operation2 = new Operation();
            operation2.setOperationCategory(OperationCategories.EXPENSE);
            operation2.setAmountOfMoney(50000.0);
            operation2.setCategory("car purchase");
            operation2.setAccount(account2);

            user1.getAccountList().add(account1);
            user1.getAccountList().add(account2);

            account1.getOperationList().add(operation1);
            account2.getOperationList().add(operation2);

            entityManager.persist(user1);
/*            entityManager.persist(account1);
            entityManager.persist(account2);
            entityManager.persist(operation1);
            entityManager.persist(operation2);*/

            entityManager.getTransaction().commit();
        }
    }
}
