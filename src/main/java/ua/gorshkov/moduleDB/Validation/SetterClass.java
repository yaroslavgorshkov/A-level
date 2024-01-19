package ua.gorshkov.moduleDB.Validation;

import ua.gorshkov.moduleDB.Entities.Enums.OperationCategories;
import ua.gorshkov.moduleDB.Validation.DoubleValidationStrategies;
import ua.gorshkov.moduleDB.Validation.StringValidationStrategies;

import java.util.Arrays;
import java.util.Scanner;

public class SetterClass {
    private static final Scanner scanner = new Scanner(System.in);

    private static String cleanString(String string) {
        string = string.trim();
        string = string.replaceAll("\\s{2,}", " ");
        return string;
    }

    public static String setString(String message, StringValidationStrategies... validationStrategies) {
        String string;
        while (true) {
            try {
                System.out.print(message);
                string = scanner.nextLine();
                string = cleanString(string);
                if (Arrays.asList(validationStrategies).contains(StringValidationStrategies.NOT_EMPTY)) {
                    if (string.isEmpty()) {
                        throw new RuntimeException("String is empty!");
                    }
                }
                if (Arrays.asList(validationStrategies).contains(StringValidationStrategies.CAPITALIZATION)) {
                    if (!Character.isUpperCase(string.charAt(0))) {
                        throw new RuntimeException("The line does not start with a capital letter!");
                    }
                }
                if (Arrays.asList(validationStrategies).contains(StringValidationStrategies.ALPHABETIC_ONLY)) {
                    for (int i = 0; i < string.length(); i++) {
                        if ((!Character.isAlphabetic(string.charAt(i))) && !(string.charAt(i) == ' ')) {
                            throw new RuntimeException("The line contains more than just letters!");
                        }
                    }
                }
                if (Arrays.asList(validationStrategies).contains(StringValidationStrategies.NUMERIC_ONLY)) {
                    for (int i = 0; i < string.length(); i++) {
                        if (!Character.isDigit(string.charAt(i))) {
                            throw new RuntimeException("The line contains more than just digits!");
                        }
                    }
                }
                if (Arrays.asList(validationStrategies).contains(StringValidationStrategies.WITHOUT_PASSES)) {
                    if (string.contains(" ")) {
                        throw new RuntimeException("The line contains spaces!");
                    }
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return string;
    }

    public static double setDouble(String message, DoubleValidationStrategies... doubleValidationStrategies) {
        double aDouble;
        while (true) {
            System.out.print(message);
            try {
                aDouble = scanner.nextDouble();
                if (Arrays.asList(doubleValidationStrategies).contains(DoubleValidationStrategies.GREATER_OR_EQUAL_ZERO)) {
                    if (aDouble < 0) {
                        throw new RuntimeException("Number less than 0!");
                    }
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return aDouble;
    }

    public static OperationCategories setOperationCategory(String message) {
        int operationCategory;
        while (true) {
            System.out.print(message);
            try {
                operationCategory = scanner.nextInt();
                if (operationCategory == 0) {
                    return OperationCategories.EXPENSE;
                } else if (operationCategory == 1) {
                    return OperationCategories.INCOME;
                } else {
                    throw new RuntimeException("Incorrect indication of the type of operation");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
