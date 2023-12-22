package ua.gorshkov.hw17;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.SessionFactory;
import org.hibernate.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.LocalTime;
import java.util.List;

public class ForthEx {
    public static void main(String[] args) {
        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit")) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            for (int i = 0; i < 10; i++) {
                Person person = new Person();
                person.setName("Person Name" + i);
                person.setSurname("Person Surname" + i);
                entityManager.persist(person);
            }

            List<Person> personList = entityManager.createQuery("select p from Person p", Person.class).getResultList();
            System.out.println(personList);

            int j = 10;
            for (Person person : personList) {
                for(int i = 0; i < j; i++) {
                    PersonOrder personOrder = new PersonOrder();
                    personOrder.setOrderInfo("Some thing№" + i);
                    personOrder.setOrderTime(LocalTime.now().plusHours(i));
                    personOrder.setPerson(person);
                    entityManager.persist(personOrder);
                }
                j--;
            }

            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();

            List<Person> listWithPersonThatOrderAmountBiggerThanFive =
                    entityManager.createQuery("select p from Person p where size(p.orderList) > 5", Person.class).getResultList();

            System.out.println("Persons who has more than 5 orders:");
            System.out.println(listWithPersonThatOrderAmountBiggerThanFive);

            List<PersonOrder> person0OrdersBetweenSomeTime =
                    entityManager.createQuery("select po from PersonOrder po " +
                                    "where po.person = :person " +
                                    "and po.orderTime between :startTime and :endTime", PersonOrder.class)
                            .setParameter("person", personList.get(0))
                            .setParameter("startTime", LocalTime.now().plusHours(2))
                            .setParameter("endTime", LocalTime.now().plusHours(6))
                            .getResultList();

            System.out.println("Person 0 orders at a specified time:");
            System.out.println(person0OrdersBetweenSomeTime);

            System.out.println();

            for(Person person : personList) {
                int orderCounter =
                        entityManager.createQuery("select po from PersonOrder po " +
                                "where po.person = :person ",
                                        PersonOrder.class)
                        .setParameter("person", person).getResultList().size();
                System.out.println("Person " + person + " has " + orderCounter + " order(s)!");
            }

            entityManager.getTransaction().commit();
        }
    }
}
