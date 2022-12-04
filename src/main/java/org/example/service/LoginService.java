package org.example.service;

import org.example.model.Customer;

public interface LoginService {
    Customer findByEmail(String email) throws Exception;
    void setLoggedIn(int id) throws Exception;
    void setLoggedOut(int id) throws Exception;
    void deActivateUser(int id) throws Exception;
    void incrementWrongPassTrials(int id) throws Exception;
}
