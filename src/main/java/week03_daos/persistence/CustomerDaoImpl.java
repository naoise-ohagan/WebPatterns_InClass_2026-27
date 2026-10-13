package week03_daos.persistence;

import org.jspecify.annotations.NonNull;
import solutions.dao_exercises.entities.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    private MySqlConnector mySqlConnector;

    public CustomerDaoImpl(MySqlConnector)


    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/classicmodels";
    private String username = "root";
    private String password = "";

    public List<Customer> selectCustomersByName(String name) {
        List<Customer> customers = new ArrayList<>();

        MySqlConnector mySqlConnector = new MySqlConnector();
        Connection conn = mySqlConnector.getConnection();
        if (conn == null) {
            System.out.println("No connection available!");
            return customers;
        }

        // Prepare statement - Write an SQL statement and compile it into something
        // the database can actually run
        String sql = "SELECT * FROM customers WHERE customerName = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            // Run query - Execute the SQL that has been compiled and get the results
            try (ResultSet rs = ps.executeQuery()) {
                // Process results - loop through each row in resultset until it's empty
                while (rs.next()) {
                    // Extract pieces from the result row - we can explicitly ask for each piece by column name
                    Customer customer = mapRow(rs);
                    customers.add(customer);
                }
            } catch (SQLException e) {
                System.out.println("Exception: \"" + e.getMessage() + "\"");
                System.out.println("\tIssue occurred when processing query or results");
            }
        } catch (SQLException e) {
            System.out.println("Exception: \"" + e.getMessage() + "\"");
            System.out.println("\tCannot prepare statement: " + sql);
        }

        return customers;
    }

    private static @NonNull Customer mapRow(ResultSet rs) throws SQLException {
        Customer customer = new Customer(
                rs.getInt("customerNumber"),
                rs.getString("customerName"),
                rs.getString("contactLastName"),
                rs.getString("contactFirstName"),
                rs.getString("phone"),
                rs.getString("addressLine1"),
                rs.getString("addressLine2"),
                rs.getString("city"),
                rs.getString("state"),
                rs.getString("postalCode"),
                rs.getString("country"),
                rs.getInt("salesRepEmployeeNumber"),
                rs.getDouble("creditLimit")
        );
        return customer;
    }

    @Override
    public List<Customer> selectCustomersContainingName(String name) {
        List<Customer> customers = new ArrayList<>();

        MySqlConnector mySqlConnector = new MySqlConnector();
        Connection conn = mySqlConnector.getConnection();

        // Prepare statement - Write an SQL statement and compile it into something
        // the database can actually run
        String sql = "SELECT * FROM customers WHERE customerName LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            // Run query - Execute the SQL that has been compiled and get the results
            try (ResultSet rs = ps.executeQuery()) {
                // Process results - loop through each row in resultset until it's empty
                while (rs.next()) {
                    // Extract pieces from the result row - we can explicitly ask for each piece by column name
                    Customer customer = mapRow(rs);
                    customers.add(customer);
                }
            } catch (SQLException e) {
                System.out.println("Exception: \"" + e.getMessage() + "\"");
                System.out.println("\tIssue occurred when processing query or results");
            }
        } catch (SQLException e) {
            System.out.println("Exception: \"" + e.getMessage() + "\"");
            System.out.println("\tCannot prepare statement: " + sql);
        }
        try{
            mySqlConnector.freeConnection();
        } catch (SQLException e) {
            System.out.println("Could not close the connection to database. error reads: " + e.getMessage());
        }
        return customers;
    }

    @Override
    public week03_daos.entities.Customer findCustomerById(int customerNumber) {
        return null;
    }

    @Override
    public boolean addCustomer(week03_daos.entities.Customer c) {
        return false;
    }

    static void main(String[] args) {
        MySqlConnector mySqlConnector = new MySqlConnector();
        CustomerDao customerDao = new CustomerDaoImpl(mySqlConnector);
        List<Customer> customers = customerDao.selectCustomersByName("Atelier Graphique");

        System.out.println("Customers with matching Atelier Graphique:");
        System.out.println(customers);

        customers = customerDao.selectCustomersContainingName("Z");
        System.out.println("Customers with name containing z:");
        System.out.println(customers);
    }
}
