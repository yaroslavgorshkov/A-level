package ua.gorshkov.hw15.ex2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class PersonFactory {
    public Person createPerson(String name, int age, String address) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Person> personClass = Person.class;
        Constructor<Person> personConstructor = personClass.getConstructor(String.class, int.class, String.class);
        return personConstructor.newInstance(name, age, address);
    }

    public void printFields(Object obj) throws NoSuchFieldException, IllegalAccessException {
        Person person = (Person) obj;
        Field nameField = Person.class.getDeclaredField("name");
        nameField.setAccessible(true);
        Field ageField = Person.class.getDeclaredField("age");
        ageField.setAccessible(true);
        Field addressField = Person.class.getDeclaredField("address");
        addressField.setAccessible(true);
        System.out.println("Person:" +
                "\nAge: " + nameField.get(person) + ", class: " + nameField.get(person).getClass() +
                "\nAge: " + ageField.get(person) + ", class: " + ageField.get(person).getClass() +
                "\nAddress: " + addressField.get(person) + ", class: " + addressField.get(person).getClass() +
                "\n");
    }
}
