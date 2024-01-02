package ua.gorshkov.hw18.ex1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/ua/gorshkov/hw18/ex1/file.txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        Person person = new Person(21, "Kolya");
        ObjectMapper objectMapper = new ObjectMapper();
        String personJson = objectMapper.writeValueAsString(person);
        randomAccessFile.writeBytes(personJson);
        randomAccessFile.seek(0);
        String newPersonJson = randomAccessFile.readLine();
        Person person2 = objectMapper.readValue(newPersonJson, Person.class);
        System.out.println("Person 1 = " + person);
        System.out.println("Person 2 = " + person2);
        randomAccessFile.close();
    }
}
