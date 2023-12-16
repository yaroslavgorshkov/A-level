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
        Field[] fields = person.getClass().getDeclaredFields();
        System.out.println("Person:");
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println(field.getName() + ": " + field.get(person) + ", class: " + field.get(person).getClass());
        }
    }
}
