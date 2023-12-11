package ua.gorshkov.hw15.ex2;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        PersonFactory personFactory = new PersonFactory();
        Person person = personFactory.createPerson("Yarik", 18, "Krivoy Rog");
        personFactory.printFields(person);
    }
}
