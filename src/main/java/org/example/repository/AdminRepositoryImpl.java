package org.example.repository;

import org.example.model.Customer;
import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminRepositoryImpl implements AdminRepository{
    @Override
    public List<Customer> showAllAdmins() {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Customer WHERE isAdmin= :isAdmin ");
            query.setParameter("isAdmin", true);
            List<Customer> listOfAdmins = query.list();
            transaction.commit();
            return listOfAdmins;
        }finally {
            sessionFactory.close();
            session.close();
        }
    }

    @Override
    public void addAdmin(Customer customer) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            System.out.println("Inserted Done!!!");
        }finally {
            sessionFactory.close();
            session.close();
        }
    }

    @Override
    public void deleteAdminById(int id) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query =session.createQuery("delete from Customer where customerId = :id");
            query.setParameter("id",id);
            query.executeUpdate();
            transaction.commit();
            System.out.println("Deleted Done!!!");
        }finally {
            sessionFactory.close();
            session.close();
        }
    }

    @Override
    public void updateAdmin(int id, Customer customer) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query =session.createQuery("update Customer set firstName= :firstName , lastName= :lastName, email= :email , password= :password ,isAdmin= :isAdmin where customerId= :id");
            query.setParameter("firstName",customer.getFirstName());
            query.setParameter("lastName",customer.getLastName());
            query.setParameter("email",customer.getEmail());
            query.setParameter("password",customer.getPassword());
            query.setParameter("isAdmin",customer.getIsAdmin());
            query.setParameter("id",id);
            query.executeUpdate();
            transaction.commit();
            System.out.println("Updated Done!!!");
        }finally {
            sessionFactory.close();
            session.close();
        }
    }

    @Override
    public Customer getCustomerById(int id) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Customer customer = null;
        try {
            Transaction transaction = session.beginTransaction();
            Query<Customer> query = session.createQuery("FROM Customer WHERE customerId = :id" , Customer.class);
            session.setCacheMode(CacheMode.IGNORE);
            query.setParameter("id",id);
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
}
