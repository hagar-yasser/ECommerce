package org.example.repository;

import org.example.model.Customer;

public interface LoginRepository {
    Customer findByEmail(String email);
    void setLoggedIn(int id);
    void deActivateUser(int id);
    void incrementWrongPassTrials(int id);
}
