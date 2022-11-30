package org.example.service;

import org.example.model.Customer;
import org.example.model.VerificationToken;
import org.example.repository.UserRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRegisterService {

    private UserRegisterRepository userRegisterRepository;



    @Autowired
    public UserRegisterService(UserRegisterRepository userRegisterRepository) {
        this.userRegisterRepository = userRegisterRepository;
    }

    public void registerUser(Customer customer){

        userRegisterRepository.registerUser(customer);
    }

    public Customer findByEmail(String email){
        return userRegisterRepository.findByEmail(email);
    }

    public void createVerificationToken(String token,Customer customer) {
        VerificationToken newUserToken = new VerificationToken(token,customer);
        tokenDAO.save(newUserToken);
    }
}
