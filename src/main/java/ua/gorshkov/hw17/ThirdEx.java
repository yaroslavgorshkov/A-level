package ua.gorshkov.hw17;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalTime;
import java.util.List;

public class ThirdEx {
    public static void main(String[] args) {
        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit")) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Person person1 = new Person();
            person1.setName("yarik");
            person1.setSurname("gorkov");

            Person person2 = new Person();
            person2.setName("oleh");
            person2.setSurname("skripka");

            PersonOrder order1 = new PersonOrder();
            order1.setOrderInfo("tv-set");
            order1.setOrderTime(LocalTime.now().plusHours(1L));
            order1.setPerson(person1);

            PersonOrder order2 = new PersonOrder();
            order2.setOrderInfo("candies");
            order2.setOrderTime(LocalTime.now().plusHours(1L));
            order2.setPerson(person2);

            PersonOrder order3 = new PersonOrder();
            order3.setOrderInfo("playstation");
            order3.setOrderTime(LocalTime.now().plusHours(2L));
            order3.setPerson(person2);

            entityManager.persist(order1);
            entityManager.persist(order2);
            entityManager.persist(order3);

            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();

            Person getPerson1 = entityManager.find(Person.class, 1L);
            List<PersonOrder> person1Orders = getPerson1.getOrderList();

            Person getPerson2 = entityManager.find(Person.class, 2L);
            List<PersonOrder> person2Orders = getPerson2.getOrderList();

            System.out.println("Person 1 info: " + getPerson1);
            System.out.println("Person 1 orders: " + person1Orders);

            System.out.println("Person 2 info: " + getPerson2);
            System.out.println("Person 2 orders: " + person2Orders);

            entityManager.getTransaction().commit();
        }
    }
}
