package ua.gorshkov.hw15.ex1;

import java.lang.reflect.Field;

public class StudentInfoPrinter {
    public void printStudentInfo(Student student) throws NoSuchFieldException, IllegalAccessException {
        Class<Student> studentClass = Student.class;
        Field firstNameField = studentClass.getDeclaredField("firstName");
        Field lastNameField = studentClass.getDeclaredField("lastName");
        Field ageField = studentClass.getDeclaredField("age");

        if (firstNameField.getAnnotation(ShowInfo.class).show()) {
            firstNameField.setAccessible(true);
            System.out.println("Name: {" + firstNameField.get(student) + "}");
        }
        if (lastNameField.getAnnotation(ShowInfo.class).show()) {
            lastNameField.setAccessible(true);
            System.out.println("Last name: {" + lastNameField.get(student) + "}");
        }
        if (ageField.getAnnotation(ShowInfo.class).show()) {
            ageField.setAccessible(true);
            System.out.println("Age: {" + ageField.get(student) + "}");
        }
    }
}
