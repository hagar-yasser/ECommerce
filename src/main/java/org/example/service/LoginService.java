package org.example.service;

import org.example.model.Customer;

public interface LoginService {
    Customer findByEmail(String email);
    void setLoggedIn(int id);
    void setLoggedOut(int id);
    void deActivateUser(int id);
    void incrementWrongPassTrials(int id);
}
