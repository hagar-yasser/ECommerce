package org.example.service;

import org.example.model.Customer;
import org.example.model.VerificationToken;
import org.example.repository.UserRegisterRepository;
import org.example.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRegisterServiceImpl implements UserRegisterService {

    private UserRegisterRepository userRegisterRepository;
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    public UserRegisterServiceImpl(UserRegisterRepository userRegisterRepository,
                                   VerificationTokenRepository verificationTokenRepository) {

        this.userRegisterRepository = userRegisterRepository;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public void registerUser(Customer customer){

        userRegisterRepository.registerUser(customer);
    }

    @Override
    public Customer findByEmail(String email){
        return userRegisterRepository.findByEmail(email);
    }

//    @Override
//    public void createVerificationToken(String token,Customer customer) {
//        VerificationToken newUserToken = new VerificationToken(token,customer);
//        verificationTokenRepository.save(newUserToken);
//    }

    @Override
    public void sendVerification() {

    }
}
