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
    public Customer findByEmail(String email) throws Exception {
        try {
            return loginRepository.findByEmail(email);
        }catch (Exception e){
            throw new Exception("Couldn't get Items from the database");
        }
    }

    @Override
    public void setLoggedIn(int id) throws Exception {
        try {
            loginRepository.setLoggedIn(id);
        }catch (Exception e){
            throw new Exception("Couldn't get Items from the database");
        }
    }

    @Override
    public void setLoggedOut(int id) throws Exception {
        try {
            loginRepository.setLoggedOut(id);
        }catch (Exception e){
            throw new Exception("Couldn't get Items from the database");
        }
    }

    @Override
    public void deActivateUser(int id) throws Exception {
        try {
            loginRepository.deActivateUser(id);
        }catch (Exception e){
            throw new Exception("Couldn't get Items from the database");
        }
    }

    @Override
    public void incrementWrongPassTrials(int id) throws Exception {
        try {
            loginRepository.incrementWrongPassTrials(id);
        }catch (Exception e){
            throw new Exception("Couldn't get Items from the database");
        }
    }
}
