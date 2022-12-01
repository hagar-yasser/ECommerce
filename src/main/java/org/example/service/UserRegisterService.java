package org.example.service;

import org.example.model.Customer;
import org.example.model.VerificationToken;

public interface UserRegisterService {
    void registerUser(Customer customer);
    Customer findByEmail(String email);
    void sendVerification();

}
