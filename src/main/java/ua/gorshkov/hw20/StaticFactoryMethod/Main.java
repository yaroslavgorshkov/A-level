package ua.gorshkov.hw20.StaticFactoryMethod;

public class Main {
    public static void main(String[] args) {
        Person person1 = Person.fromName("Yarik");
        Person person2 = Person.fromSurname("Gorshkoff");
        Person person3 = Person.personBuilder("Ivan", "Ivanov", 18);
    }
}
