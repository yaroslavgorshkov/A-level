package ua.gorshkov.hw17;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class SecondEx {
    public static void main(String[] args) {
        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit")) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();

            Person person1 = new Person();
            person1.setName("firstName");
            person1.setSurname("firstSurname");
            entityManager.persist(person1);

            Person person2 = entityManager.find(Person.class, person1.getId());
            person2.setName("secondName");
            person2.setSurname("secondSurname");

            Person personForDelete = new Person();
            personForDelete.setName("Oh no");
            personForDelete.setSurname("I will be deleted(");
            entityManager.persist(personForDelete);

            Person findDeletedperson = entityManager.find(Person.class, personForDelete.getId());
            entityManager.remove(findDeletedperson);

            entityManager.getTransaction().commit();
        }
    }
}
