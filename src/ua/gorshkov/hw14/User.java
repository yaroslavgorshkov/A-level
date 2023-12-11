package ua.gorshkov.hw14;

public class User
{
    public String name;
    public String secondName;
    public double salary;
    public int age;

    public User(String name, String secondName, Double salary, Integer age) {
        this.name = name;
        this.secondName = secondName;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}
