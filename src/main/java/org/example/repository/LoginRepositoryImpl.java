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
    private SessionFactory sessionFactory;

    public LoginRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Customer findByEmail(String email) {
        Customer customer = null;
        try(Session session=sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Customer> query = session.createQuery("from Customer where email=:email", Customer.class);
            session.setCacheMode(CacheMode.IGNORE);
            query.setParameter("email", email);
            customer = query.getSingleResult();
            transaction.commit();
        }catch (Exception e) {
            customer = null;
        }
        return customer;
    }

    @Override
    public void setLoggedIn(int id) {
        try (Session session=sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Query query =session.createQuery("update Customer set isLoggedIn= :isLoggedIn , wrongPasswordTrials=0 where customerId= :id");
            query.setParameter("isLoggedIn", true);
            query.setParameter("id",id);
            query.executeUpdate();
            transaction.commit();
        }
    }
    public void setLoggedOut(int id) {
        try (Session session=sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Query query =session.createQuery("update Customer set isLoggedIn= :isLoggedIn where customerId= :id");
            query.setParameter("isLoggedIn", false);
            query.setParameter("id",id);
            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void deActivateUser(int id) {
        try (Session session=sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Query query =session.createQuery("update Customer set isActivated= :isActivated where customerId= :id");
            query.setParameter("isActivated", false);
            query.setParameter("id",id);
            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void incrementWrongPassTrials(int id) {
        try (Session session=sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Query query =session.createQuery("UPDATE Customer SET wrongPasswordTrials = wrongPasswordTrials + 1 WHERE customerId = :id" );
            query.setParameter("id",id);
            query.executeUpdate();
            transaction.commit();
        }
    }
}
