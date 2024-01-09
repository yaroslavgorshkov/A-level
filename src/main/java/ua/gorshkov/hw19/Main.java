package ua.gorshkov.hw19;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PersonDBManager personDBManager = new PersonDBManager();
        Person person1 = new Person();
        person1.setName("Yarik3");
        person1.setSalary(1000);
        personDBManager.save(person1);

        Person person2 = new Person();
        person2.setName("Yarik2");
        person2.setSalary(2000);
        personDBManager.save(person2);

        Person person3 = personDBManager.get(15L);
        person3.setSalary(15000);
        personDBManager.update(person3);

        Person person4 = personDBManager.get(16L);
        personDBManager.delete(person4);

        List<Person> personList = personDBManager.getAll();
        System.out.println(personList);
    }
}
