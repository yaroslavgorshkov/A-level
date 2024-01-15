package ua.gorshkov.hw19;

import java.util.List;
import java.util.Optional;

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

        Optional<Person> person3Optional = personDBManager.get(1L);
        if (person3Optional.isPresent()) {
            person3Optional.get().setSalary(15000);
            personDBManager.update(person3Optional.get());
        } else {
            System.out.println("Person3 is null! You cannot do anything!");
        }

        Optional<Person> person4Optional = personDBManager.get(2L);
        if (person4Optional.isPresent()) {
            personDBManager.delete(person4Optional.get());
        } else {
            System.out.println("Person4 is null! You cannot do anything!");
        }

        List<Person> personList = personDBManager.getAll();
        System.out.println(personList);
    }
}
