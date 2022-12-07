package org.example.repository;

import org.example.model.Customer;
import org.example.model.VerificationToken;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class VerificationTokenRepository {

    //need a function to save a verification token in database
    public VerificationToken findByVerificationToken(String token) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query<VerificationToken> query = session.createQuery("from VerificationToken where token=:token");
        query.setParameter("token", token);
        VerificationToken userToken = null;
        try {
            userToken = query.getSingleResult();
        } catch (Exception e) {
            userToken = null;
            e.printStackTrace();
        }
        return userToken;
    }


    public VerificationToken findByUser(Customer customer) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(VerificationTokenRepository.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();
        Query<VerificationToken> query = session.createQuery("from VerificationToken where user_id=:user_id ");
        query.setParameter("user_id", customer.getCustomerId());

        VerificationToken userToken = null;
        try {
            userToken = query.getSingleResult();
        } catch (Exception e) {
            userToken = null;
            e.printStackTrace();
        }
        return userToken;
    }

    public void save(VerificationToken verificationToken) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(VerificationToken.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(verificationToken);
            transaction.commit();
        }finally {
            sessionFactory.close();
            session.close();
        }
    }
}
