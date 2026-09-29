package week03_daos.apps;

import solutions.intro_to_jdbc.apps.ProductDataAccess;
import week03_daos.entities.Product;

import java.util.List;
import java.util.Scanner;

public class SampleSelectProductsByProductLine {
    static void main() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter product line: ");
        String productLine = input.nextLine();

        List<Product> products = ProductDataAccess.getProductsByProductLine(productLine);

        if(!products.isEmpty()){
            System.out.println("Product list for "+ productLine + ":");
            for (Product product : products) {
                System.out.println(product);
            }
        }else{
            System.out.println("No products found.");
        }
    }


}
