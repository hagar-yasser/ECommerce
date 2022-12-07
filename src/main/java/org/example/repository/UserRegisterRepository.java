package org.example.repository;

import org.apache.catalina.User;
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

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRegisterRepository {

    private UserRegisterService userRegisterService;

    @Autowired
    public UserRegisterRepository(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

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
//            String hashPassword = userRegisterService.hashPassword(customer);
//            Query query = session.createQuery("INSERT INTO Customer (customerId, firstName, lastName,email" +
//                    "password,isAdmin,isLoggedIn,isActivated,wrongPasswordTrials) " +
//                    "VALUES ?, ?, ?,?\" +\n" +
//                    "\"?,?,?,?," +
//                    "?");
//            query.setParameter("1",customer.getCustomerId());
//            query.setParameter("2",customer.getFirstName());
//            query.setParameter("3",customer.getLastName());
//            query.setParameter("4",customer.getEmail());
//            query.setParameter("5",hashPassword);
//            query.setParameter("6",customer.getIsAdmin());
//            query.setParameter("7",customer.getIsLoggedIn());
//            query.setParameter("8",customer.getIsActivated());
//            query.setParameter("9",customer.getWrongPasswordTrials());
//            query.executeUpdate();

            session.save(customer);
            transaction.commit();
        }finally {
            sessionFactory.close();
            session.close();
        }


    }

}
