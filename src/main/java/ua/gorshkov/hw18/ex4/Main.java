package ua.gorshkov.hw18.ex4;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.bson.Document;
import ua.gorshkov.hw18.ex4.Employee;

public class Main {
    public static void main(String[] args) {
        try (EntityManagerFactory entityManagerFactory
                     = Persistence.createEntityManagerFactory("my-persistence-unit");
             MongoClient mongoClient = MongoClients.create("mongodb://yarik:yarik@localhost:27017")) {
            // mongo
            MongoDatabase mongoDatabase = mongoClient.getDatabase("mydb");
            MongoCollection<Document> collection = mongoDatabase.getCollection("hw18_collection");
            // jakarta
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            // code
            ua.gorshkov.hw18.ex4.Employee employee = new ua.gorshkov.hw18.ex4.Employee();
            employee.setName("yarik");
            // inserting
            Document personDocument = new Document()
                    .append("name", employee.getName());
            collection.insertOne(personDocument);

            entityManager.persist(employee);
            // finding
            Document foundDocument = collection.find(new Document("name", employee.getName())).first();
            System.out.println("Found doc = " + foundDocument);

            ua.gorshkov.hw18.ex4.Employee foundEmployee = entityManager.find(ua.gorshkov.hw18.ex4.Employee.class, 1L);
            System.out.println("Found employee" + foundEmployee);
            // updating
            collection.updateOne(new Document("name", employee.getName()), new Document("$set", new Document("name", "Yarik Mongo")));
            employee.setName("Yarik Hibernate");
            // deleting
            collection.deleteOne(new Document("name", "Yarik Mongo"));
            entityManager.remove(employee);
            // end
            entityManager.getTransaction().commit();
        }
    }
}