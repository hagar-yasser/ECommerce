package org.example.repository;

import org.example.model.Customer;
import org.example.model.VerificationToken;
import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.ArrayList;
import java.util.List;

public class UserRegisterRepository {

    private JavaMailSender javaMailSender;

    public Customer findByEmail(String email){
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query<Customer> query = session.createQuery("from Customer where email=:email", Customer.class);
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
            session.save(customer);
            transaction.commit();
            System.out.println("user is registered");
        }finally {
            sessionFactory.close();
            session.close();
        }


    }
}
