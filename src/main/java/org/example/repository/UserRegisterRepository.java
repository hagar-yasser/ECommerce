package org.example.repository;

import org.apache.catalina.User;
import org.checkerframework.checker.units.qual.C;
import org.example.model.Customer;
import org.example.model.VerificationToken;
import org.example.service.UserRegisterService;
import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRegisterRepository {

    public Customer findByEmail(String email){
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query<Customer> query = session.createQuery("from Customer where email=:email ", Customer.class);
        session.setCacheMode(CacheMode.IGNORE);
        query.setParameter("email", email);
        Customer customer = null;
        try {
            customer = query.getSingleResult();
        }catch (Exception e) {
            customer = null;
        }
        return customer;
    }

    public void registerUser(Customer customer){
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();

            //hashing password

//            String hashPassword = hashPassword(customer);
//            Query query = session.createQuery("INSERT INTO Customer (customerId, firstName, isActivated,isAdmin" +
//                    "isLoggedIn,lastName,password,wrongPasswordTrials) " +
//                    "values (?, ?, ?,?,?,?,?,?,?)");
//            query.setParameter("1",customer.getCustomerId());
//            query.setParameter("2",customer.getFirstName());
//            query.setParameter("3",customer.getIsActivated());
//            query.setParameter("4",customer.getIsAdmin());
//            query.setParameter("5",customer.getIsLoggedIn());
//            query.setParameter("6",customer.getLastName());
//            query.setParameter("7",hashPassword);
//            query.setParameter("8",customer.getPassword());
//            query.setParameter("9",customer.getWrongPasswordTrials());
//            query.executeUpdate();

            session.save(customer);
            transaction.commit();
        }finally {
            sessionFactory.close();
            session.close();
        }


    }

    public String hashPassword(Customer customer){

        String password = customer.getPassword();
        /* MessageDigest instance for hashing using SHA256 */
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        /* digest() method called to calculate message digest of an input and return array of byte */
        byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));

        /* Convert byte array of hash into digest */
        BigInteger number = new BigInteger(1, hash);

        /* Convert the digest into hex value */
        StringBuilder hexString = new StringBuilder(number.toString(16));

        /* Pad with leading zeros */
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }
        return hexString.toString();

    }

}
