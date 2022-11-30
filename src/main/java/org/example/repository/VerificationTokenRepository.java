package org.example.repository;

import org.example.model.Customer;
import org.example.model.VerificationToken;
import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class VerificationTokenRepository {

    public VerificationToken findByToken(String token) {
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
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();
        Query<VerificationToken> query = session.createQuery("from VerificationToken where user=:user");
        query.setParameter("customer", customer);

        VerificationToken userToken = null;
        try {
            userToken = query.getSingleResult();
        } catch (Exception e) {
            userToken = null;
            e.printStackTrace();
        }
        return userToken;
    }

    public void save(VerificationToken newUserToken) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        session.setCacheMode(CacheMode.IGNORE);
        session.saveOrUpdate(newUserToken);
    }
}
