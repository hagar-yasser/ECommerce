package org.example.service;

import org.example.model.Customer;
import org.example.model.VerificationToken;
import org.example.repository.UserRegisterRepository;
import org.example.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRegisterService {

    private UserRegisterRepository userRegisterRepository;
    private VerificationTokenRepository verificationTokenRepository;

    public UserRegisterService(UserRegisterRepository userRegisterRepository,
                               VerificationTokenRepository verificationTokenRepository) {

        this.userRegisterRepository = userRegisterRepository;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    public UserRegisterService(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

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
        verificationTokenRepository.save(newUserToken);
    }
}
