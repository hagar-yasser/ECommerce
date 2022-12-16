package org.example.service;

import org.example.model.Customer;
import org.example.repository.UserRegisterRepository;
import org.example.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {

    private UserRegisterRepository userRegisterRepository;
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    public UserRegisterServiceImpl(UserRegisterRepository userRegisterRepository,
                                   VerificationTokenRepository verificationTokenRepositoryf) {

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
//    public String hashPassword(Customer customer){
//
//        String password = customer.getPassword();
//
//        /* MessageDigest instance for hashing using SHA256 */
//        MessageDigest md = null;
//        try {
//            md = MessageDigest.getInstance("SHA-256");
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//
//        /* digest() method called to calculate message digest of an input and return array of byte */
//        byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
//
//        /* Convert byte array of hash into digest */
//        BigInteger number = new BigInteger(1, hash);
//
//        /* Convert the digest into hex value */
//        StringBuilder hexString = new StringBuilder(number.toString(16));
//
//        /* Pad with leading zeros */
//        while (hexString.length() < 32)
//        {
//            hexString.insert(0, '0');
//        }
//        return hexString.toString();
//
//    }


//    @Override
//    public void createVerificationToken(String token,Customer customer) {
//        VerificationToken newUserToken = new VerificationToken(token,customer);
//        verificationTokenRepository.save(newUserToken);
//    }




}
