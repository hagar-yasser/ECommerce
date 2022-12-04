package org.example.repository;

import org.example.model.Customer;
import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepositoryImpl implements LoginRepository{
    @Override
    public Customer findByEmail(String email) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Customer customer = null;
        try {
            Transaction transaction = session.beginTransaction();
            Query<Customer> query = session.createQuery("from Customer where email=:email", Customer.class);
            session.setCacheMode(CacheMode.IGNORE);
            query.setParameter("email", email);
            customer = query.getSingleResult();
            transaction.commit();
        }catch (Exception e) {
            customer = null;
        }finally {
            sessionFactory.close();
            session.close();
        }
        return customer;
    }

    @Override
    public void setLoggedIn(int id) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query =session.createQuery("update Customer set isLoggedIn= :isLoggedIn , wrongPasswordTrials=0 where customerId= :id");
            query.setParameter("isLoggedIn", true);
            query.setParameter("id",id);
            query.executeUpdate();
            transaction.commit();
        }finally {
            sessionFactory.close();
            session.close();
        }
    }
    public void setLoggedOut(int id) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query =session.createQuery("update Customer set isLoggedIn= :isLoggedIn where customerId= :id");
            query.setParameter("isLoggedIn", false);
            query.setParameter("id",id);
            query.executeUpdate();
            transaction.commit();
        }finally {
            sessionFactory.close();
            session.close();
        }
    }

    @Override
    public void deActivateUser(int id) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query =session.createQuery("update Customer set isActivated= :isActivated where customerId= :id");
            query.setParameter("isActivated", false);
            query.setParameter("id",id);
            query.executeUpdate();
            transaction.commit();
        }finally {
            sessionFactory.close();
            session.close();
        }
    }

    @Override
    public void incrementWrongPassTrials(int id) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query =session.createQuery("UPDATE Customer SET wrongPasswordTrials = wrongPasswordTrials + 1 WHERE customerId = :id" );
            query.setParameter("id",id);
            query.executeUpdate();
            transaction.commit();
        }finally {
            sessionFactory.close();
            session.close();
        }
    }
}
