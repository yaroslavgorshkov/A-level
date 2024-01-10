package ua.gorshkov.hw20.StaticFactoryMethod;
public class Person {
    private String name;
    private String surname;
    private Integer age;

    private Person(String name, String surname, Integer age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public static Person fromName(String name) {
        return new Person(name, null, null);
    }

    public static Person fromSurname(String surname) {
        return new Person(null, surname, null);
    }

    public static Person personBuilder(String name, String surname, Integer age) {
        return new Person(name, surname, age);
    }
}

