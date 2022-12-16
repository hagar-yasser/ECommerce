package org.example.service;

import org.example.model.Customer;

public interface UserRegisterService {
    void registerUser(Customer customer);
    Customer findByEmail(String email);
}
