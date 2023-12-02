package ua.gorshkov.module2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        File input = new File("src/ua/gorshkov/module2/input.txt");
        File output = new File("src/ua/gorshkov/module2/output.txt");
        input.createNewFile();
        output.createNewFile();
        List<Product> productList = new ArrayList<>();
        Scanner scanner = new Scanner(input);
        RandomAccessFile randomAccessFile = new RandomAccessFile(output, "rw");
        while (scanner.hasNext()) {
            String[] productCharacters = scanner.nextLine().split("\\|");
            String nameOfProduct = productCharacters[0];
            int capacityOfProduct = Integer.parseInt(productCharacters[1]);
            double costOfOneProduct = Double.parseDouble(productCharacters[2]);

            productList.add(new Product(nameOfProduct, capacityOfProduct, costOfOneProduct));
        }
        randomAccessFile.writeBytes("List of products:\n");
        writeListOfProducts(productList, randomAccessFile);

        int dataThreshold = 5;
        List<Product> filteredProductList =
                filterOutProductsWithQuantitiesGreaterThanSetValue(productList, dataThreshold);
        randomAccessFile.writeBytes("List of sorted products which capacity higher than " + dataThreshold + ":\n");
        writeListOfProducts(filteredProductList, randomAccessFile);
        randomAccessFile.writeBytes("There are " + filteredProductList.size() + " product kinds in this list ^^^\n\n");

        int amountOfFood = countAllCapacity(productList);
        randomAccessFile.writeBytes("Total amount of food in the refrigerator: " + amountOfFood + "\n\n");

        double averageCostOfProducts = countAverageCostOfProducts(productList);
        randomAccessFile.writeBytes("Average cost of products: " +
                new BigDecimal(averageCostOfProducts).setScale(1, RoundingMode.HALF_UP) + "\n\n");

        List<Product> sortedProductListInDescendingOrderByPrice = sortProductsByPriceInDescendingOrder(productList);
        randomAccessFile.writeBytes("List of sorted products by price in descending:\n");
        writeListOfProducts(sortedProductListInDescendingOrderByPrice, randomAccessFile);

        double totalCostOfProducts = countTotalCostOfProducts(productList);
        randomAccessFile.writeBytes("Total cost of products: " + totalCostOfProducts + "\n");

        scanner.close();
        randomAccessFile.close();
    }

    public static void writeListOfProducts(List<Product> productList, RandomAccessFile randomAccessFile) throws IOException {
        if (productList.isEmpty()) {
            randomAccessFile.writeBytes("List is empty!\n\n");
        } else {
            for (Product product : productList) {
                randomAccessFile.writeBytes(product + "\n");
            }
            randomAccessFile.writeBytes("\n");
        }
    }

    public static List<Product> filterOutProductsWithQuantitiesGreaterThanSetValue(
            List<Product> productList, int dataThreshold) {
        return productList.stream().filter(p -> p.getCapacity() > dataThreshold).collect(Collectors.toList());
    }

    public static int countAllCapacity(List<Product> productList) {
        return productList.stream().mapToInt(p -> p.getCapacity()).sum();
    }

    public static double countAverageCostOfProducts(List<Product> productList) {
        return countTotalCostOfProducts(productList) / countAllCapacity(productList);
    }

    public static List<Product> sortProductsByPriceInDescendingOrder(List<Product> productList) {
        Comparator<Product> productPricesComparator = (p1, p2) ->
                Double.compare(p1.getCapacity() * p1.getCostOfOne(), p2.getCapacity() * p2.getCostOfOne());
        return productList.stream().sorted(productPricesComparator.reversed()).collect(Collectors.toList());
    }

    public static double countTotalCostOfProducts(List<Product> productList) {
        return productList.stream().mapToDouble(p -> (double)p.getCapacity()*p.getCostOfOne()).sum();
    }
}
