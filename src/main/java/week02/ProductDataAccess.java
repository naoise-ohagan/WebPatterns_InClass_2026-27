package week02;
import week02.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDataAccess {

    public static List<Product> getProductsByProductLine(String prodLine) {
        // Create variable to hold list of matching Products
        List<Product> products = new ArrayList<>();

        // Create variables to hold database details
        // This supports clearer intention in code, and avoids "magic" numbers/strings
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/classicmodels";
        String username = "root";
        String password = "";

        try {
            // Load driver - pull in library of Java code to work with MySQL database
            Class.forName(driver);

            // Connect to database - make a connection to the specified URL with the supplied credentials
            try(Connection conn = DriverManager.getConnection(url, username, password)){
                // Prepare statement - Write an SQL statement and compile it into something
                // the database can actually run
                String sql = "SELECT * FROM products WHERE productLine = ?";
                try(PreparedStatement ps = conn.prepareStatement(sql)){
                    // Populate placeholder
                    ps.setString(1, prodLine);
                    // Run query - Execute the SQL that has been compiled and get the results
                    try(ResultSet rs = ps.executeQuery()) {
                        // Process results - loop through each row in resultset until it's empty
                        while (rs.next()) {
                            // Extract pieces from the result row - we can explicitly ask for each piece by column name
                            String productCode = rs.getString("productCode");
                            String productName = rs.getString("productName");
                            String productLine = rs.getString("productLine");
                            String productScale = rs.getString("productScale");
                            String productVendor = rs.getString("productVendor");
                            String productDescription = rs.getString("productDescription");
                            int quantityInStock = rs.getInt("quantityInStock");
                            double buyPrice = rs.getDouble("buyPrice");
                            double msrp = rs.getDouble("msrp");

                            Product p = new Product(productCode, productName, productLine,
                                    productScale, productVendor, productDescription,
                                    quantityInStock, buyPrice, msrp);
                            products.add(p);
                        }
                    }catch(SQLException e){
                        System.out.println("Exception: \"" + e.getMessage() + "\"");
                        System.out.println("\tIssue occurred when processing query or results");
                    }
                }catch(SQLException e){
                    System.out.println("Exception: \"" + e.getMessage() + "\"");
                    System.out.println("\tCannot prepare statement: " + sql);
                }
            }catch(SQLException e){
                System.out.println("Exception: \"" + e.getMessage() + "\"");
                System.out.println("\tCannot establish a connection to " + url);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Exception: \"" + e.getMessage() + "\"");
            System.out.println("\tNo driver files found - please check dependencies.");
        }

        return products;
    }

}
