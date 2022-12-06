package org.example.repository;

import org.example.model.Customer;

import java.util.List;

public interface AdminRepository {
    List<Customer> showAllAdmins();

    void addAdmin(Customer customer);

    void deleteAdminById(int id);
    void updateAdmin(int id ,Customer customer);
    Customer getCustomerById(int id);
}
