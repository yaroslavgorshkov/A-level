package ua.gorshkov.hw15.ex1;

import java.lang.reflect.Field;

public class StudentInfoPrinter {
    public void printStudentInfo(Student student) throws NoSuchFieldException, IllegalAccessException {
        Class<Student> studentClass = Student.class;
        Field[] fields = studentClass.getDeclaredFields();
        System.out.println("Student:");
        for(Field field : fields) {
            if (field.isAnnotationPresent(ShowInfo.class)) {
                if (field.getAnnotation(ShowInfo.class).show()) {
                    field.setAccessible(true);
                    System.out.println(field.getName() + ": " + field.get(student));
                }
            }
        }
    }
}
