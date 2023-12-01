package ua.gorshkov.hw14;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("src/ua/gorshkov/hw14/input.txt");
        File outputFile = new File("src/ua/gorshkov/hw14/output.txt");
        inputFile.createNewFile();
        outputFile.createNewFile();
        List<User> userList = new ArrayList<>();
        Scanner scanner = new Scanner(inputFile);
        while (scanner.hasNext()) {
            String name = scanner.nextLine().split(":")[1].trim().replace("\"", "");
            String secondName = scanner.nextLine().split(":")[1].trim().replace("\"", "");
            double salary = Double.parseDouble(scanner.nextLine().split(":")[1].trim());
            int age = Integer.parseInt(scanner.nextLine().split(":")[1].trim());

            userList.add(new User(name, secondName, salary, age));

            if (scanner.hasNext()) {
                scanner.nextLine();
            }
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(outputFile, "rw");
        randomAccessFile.writeBytes("User List:\n");
        printUserList(userList, randomAccessFile);

        //1
        printFullNameOfUserWhichNameStartsWithA(userList, randomAccessFile);

        //2
        List<User> sortedList = sortList(userList);
        randomAccessFile.writeBytes("Sorted user List:\n");
        printUserList(sortedList, randomAccessFile);

        //3
        randomAccessFile.writeBytes("List Of Users With Min Salary:\n");
        printListOfUsersWithMinSalary(userList, randomAccessFile);

        //4
        double totalSalary = countTotalSalary(userList);
        randomAccessFile.writeBytes("Total salary = " + totalSalary + "\n");

        //5
        double differenceInSalaryBetweenTheOldestAndYoungestUsers =
                findDifferenceInSalaryBetweenTheOldestAndYoungestUsers(userList);
        if (differenceInSalaryBetweenTheOldestAndYoungestUsers != Double.MIN_VALUE) {
            randomAccessFile.writeBytes("Difference in salary between the youngest and the oldest user is " +
                    differenceInSalaryBetweenTheOldestAndYoungestUsers + "\n");
        } else {
            randomAccessFile.writeBytes("Error!\n");
        }
    }

    public static void printFullNameOfUserWhichNameStartsWithA(List<User> userList, RandomAccessFile randomAccessFile)
            throws IOException {
        Optional<User> user = userList.stream()
                .filter(u -> u.name.startsWith("A"))
                .findFirst();
        if (user.isPresent()) {
            randomAccessFile.writeBytes("First user which name starts with \"A\" is " + user.get().name
                    + " " + user.get().secondName + "\n");
        } else {
            randomAccessFile.writeBytes("There are no users with name starts with \"A\"\n\n");
        }
    }

    public static List<User> sortList(List<User> userList) {
        Comparator<User> userFirstNameComparator = (u1, u2) -> u1.name.compareTo(u2.name);
        Comparator<User> userSalaryComparator = (u1, u2) -> Double.compare(u1.salary, u2.salary);
        Comparator<User> linkedUserComparator = userFirstNameComparator.thenComparing(userSalaryComparator);

        return userList.stream()
                .sorted(linkedUserComparator)
                .collect(Collectors.toList());
    }

    public static void printUserList(List<User> userList, RandomAccessFile randomAccessFile) throws IOException {
        if (userList.isEmpty()) {
            randomAccessFile.writeBytes("List is empty!\n");
            return;
        }
        for (User user : userList) {
            randomAccessFile.writeBytes(user + "\n");
        }
        randomAccessFile.writeBytes("\n");
    }

    public static void printListOfUsersWithMinSalary(List<User> userList, RandomAccessFile randomAccessFile)
            throws IOException {
        Comparator<User> userSalaryComparator = (u1, u2) -> Double.compare(u1.salary, u2.salary);

        Optional<User> minSalaryUser = userList.stream()
                .min(userSalaryComparator);
        if (minSalaryUser.isPresent()) {
            List<User> listWithUsersWithMinSalary = userList.stream()
                    .filter(u -> u.salary == minSalaryUser.get().salary)
                    .collect(Collectors.toList());
            printUserList(listWithUsersWithMinSalary, randomAccessFile);
        } else {
            randomAccessFile.writeBytes("Error!\n");
        }
    }

    public static double countTotalSalary(List<User> userList) {
        double result = 0;
        for (User user : userList) {
            result += user.salary;
        }
        return result;
    }

    public static double findDifferenceInSalaryBetweenTheOldestAndYoungestUsers(List<User> userList) {
        Comparator<User> userAgeComparator = (u1, u2) -> Integer.compare(u1.age, u2.age);
        Optional<User> youngestUser = userList.stream().min(userAgeComparator);
        Optional<User> oldestUser = userList.stream().max(userAgeComparator);
        if (youngestUser.isPresent() && oldestUser.isPresent()) {
            return youngestUser.get().salary - oldestUser.get().salary;
        } else {
            return Double.MIN_VALUE;
        }
    }
}
