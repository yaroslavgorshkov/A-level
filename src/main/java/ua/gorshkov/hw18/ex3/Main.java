package ua.gorshkov.hw18.ex3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.*;
import jakarta.persistence.Persistence;
import org.bson.Document;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        MongoClient mongoClient = MongoClients.create("mongodb://yarik:yarik@localhost:27017");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mydb");
        MongoCollection<Document> collection = mongoDatabase.getCollection("mycollection");

        Document document = new Document()
                .append("name", "YARIK")
                .append("surname", "GORSHKOV")
                .append("age", 18)
                .append("hobby", "assembler");
        collection.insertOne(document);

        System.out.println("Document = " + document);

        Document foundDocument = collection.find(new Document("name", "YARIK")).first();
        System.out.println("Found document = " + foundDocument);

        System.out.println("Updating document 1 ...");
        collection.updateOne(new Document("name", "YARIK"), new Document("$set", new Document("age", 38)));

        Document document2 = new Document()
                .append("name", "Nastya")
                .append("surname", "Ivanova")
                .append("age", 18)
                .append("hobby", "genshin impact");
        collection.insertOne(document2);

        System.out.println("Document 2 = " + document2);

        System.out.println("Collection before deleting:");
        FindIterable<Document> documents = collection.find();
        MongoCursor<Document> cursor = documents.iterator();
        while(cursor.hasNext()) {
            Document d = cursor.next();
            System.out.println(d);
        }

        collection.deleteOne(new Document("hobby", "genshin impact"));

        System.out.println("Collection after deleting:");
        MongoCursor<Document> cursor2 = documents.iterator();
        while(cursor2.hasNext()) {
            Document d = cursor2.next();
            System.out.println(d);
        }

        mongoClient.close();
    }
}
