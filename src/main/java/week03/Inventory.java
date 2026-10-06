package week03;

import java.util.Scanner;

public class Inventory {

    Scanner scanner = new Scanner(System.in);

    static void main(String[] args) {
        String productCode = "";
        String productName = "";
        String productLine = "";
        String productScale = "";
        String productVendor = "";
        String productDescription = "";
        int quantityInStock = 2;
        double buyPrice = 20.02;
        double msrp = 44.44;

        ProductDaoImp productDao = new ProductDaoImp();

        boolean added = productDao.addProduct(productCode, productName, productLine, productScale,
                productVendor, productDescription, quantityInStock, buyPrice, msrp);

        if(added){
            System.out.println("Product " + productCode + " added to database");
        } else {
            System.out.println("Product " + productCode + " cannot be added to database.");
        }
    }
}
