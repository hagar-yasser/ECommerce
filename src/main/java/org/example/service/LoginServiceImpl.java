package org.example.service;

import org.example.model.Customer;
import org.example.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private final LoginRepository loginRepository;

    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public Customer findByEmail(String email) {
        return loginRepository.findByEmail(email);
    }

    @Override
    public void setLoggedIn(int id) {
        loginRepository.setLoggedIn(id);
    }

    @Override
    public void deActivateUser(int id) {
        loginRepository.deActivateUser(id);
    }

    @Override
    public void incrementWrongPassTrials(int id) {
        loginRepository.incrementWrongPassTrials(id);
    }
}
