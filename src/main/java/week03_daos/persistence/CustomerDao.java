package week03_daos.persistence;
import  week03_daos.entities.Customer;

import java.util.List;

    public interface CustomerDao {
        List<solutions.dao_exercises.entities.Customer> selectCustomersByName(String name);
        List<solutions.dao_exercises.entities.Customer> selectCustomersContainingName(String name);
        Customer findCustomerById(int customerNumber);
        boolean addCustomer(Customer c);
    }

