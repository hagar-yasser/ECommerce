package org.example.service;

import org.example.entity.Customer;

import java.util.List;

public interface AdminService {
    List<Customer> showAllAdmins();
    void addAdmin(Customer customer);
    void deleteAdminById(int id);
    void updateAdmin(int id ,Customer customer);
}
