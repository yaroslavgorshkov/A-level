package ua.gorshkov.hw15.ex1;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Student student = new Student("Yarik", "Gorshkov", 18);
        StudentInfoPrinter studentInfoPrinter = new StudentInfoPrinter();
        studentInfoPrinter.printStudentInfo(student);

        Method passExamSuccessfullyMethod = Student.class.getDeclaredMethod("passExamSuccessfully");
        passExamSuccessfullyMethod.setAccessible(true);
        passExamSuccessfullyMethod.invoke(student);
        Field examScoreField = Student.class.getDeclaredField("examScore");
        examScoreField.setAccessible(true);
        System.out.println("Student exam score = " + examScoreField.get(student));
    }
}
