package org.example.service;

import org.example.model.Customer;

import java.util.List;

public interface AdminService {
    List<Customer> showAllAdmins() throws Exception;
    void addAdmin(Customer customer) throws Exception;
    void deleteAdminById(int id) throws Exception;
    void updateAdmin(int id ,Customer customer) throws Exception;
    Customer getCustomerById(int id) throws Exception;
}
