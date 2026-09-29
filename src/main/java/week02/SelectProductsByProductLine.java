package week02;

import week02.entities.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Scanner;

public class SelectProductsByProductLine {

    static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter Product Line: ");
        String productLine = input.nextLine();

        List<Product> products = ProductDataAccess.getProductsByProductLine(productLine);

        if (!products.isEmpty()) {
            System.out.println("Product List for " + productLine + ":");
            for (Product product : products) {
                System.out.println(product);
            }
        } else {
            System.out.println("No products found.");
        }

    }
}
