package week02;

import week02.entities.products;
import java.sql.*;
import java.util.ArrayList;

public class SelectAllProducts {

    static void main(String[] args) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String dbqUrl = "jdbc:mysql://127.0.0.1:3306/classicmodels";
        String username = "root";
        String password = "";

        try{
            // Step 1:
            Class.forName(driver);

            // Step 2:
            try(Connection conn = DriverManager.getConnection(dbqUrl, username, password)){
                String sql = "SELECT * FROM products";
                try(PreparedStatement ps = conn.prepareStatement(sql)){

                    ArrayList<products> p = new ArrayList<>();

                    ResultSet rs = ps.executeQuery();

                    while(rs.next()){
                        String productCode = rs.getString("productCode");
                        String productName = rs.getString("productName");
                        String productLine = rs.getString("productLine");
                        String productScale = rs.getString("productScale");
                        String productVendor = rs.getString("productVendor");
                        String productDescription = rs.getString("productDescription");
                        int quantityInStock = rs.getInt("quantityInStock");
                        double buyPrice = rs.getDouble("buyPrice");
                        double MSRP = rs.getDouble("MSRP");

                        products currentproduct = new products(productCode, productName, productLine, productScale, productVendor, productDescription, quantityInStock, buyPrice, MSRP);

                        p.add(currentproduct);
                    }

                    System.out.println(p);

                }catch (SQLException e){
                    System.out.println("Exception: " + e.getMessage());
                    System.out.println("Error occurred when preparing SQL");
                }
            }catch(SQLException e){
                System.out.println("Exception: " + e.getMessage());
                System.out.println("Issue occurred when making connection to database on \"" + dbqUrl + "\" using \"" + username + "\"");
            }
        }catch(ClassNotFoundException e){
            System.out.println("Exception: " + e.getMessage());
            System.out.println("No driver found");
        }
    }
}

