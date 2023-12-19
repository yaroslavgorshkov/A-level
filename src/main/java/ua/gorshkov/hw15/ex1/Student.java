package ua.gorshkov.hw15.ex1;

public class Student {
    @ShowInfo(show = false)
    private String firstName;
    @ShowInfo
    private String lastName;
    @ShowInfo
    private int age;
    private int examScore;

    public Student(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    private void passExamSuccessfully() {
        this.examScore = 100;
    }
}
