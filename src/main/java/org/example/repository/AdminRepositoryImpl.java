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
    private SessionFactory sessionFactory;

    public AdminRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Customer> showAllAdmins() {
        try (Session session=sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Customer WHERE isAdmin= :isAdmin ");
            query.setParameter("isAdmin", true);
            List<Customer> listOfAdmins = query.list();
            transaction.commit();
            return listOfAdmins;
        }
    }

    @Override
    public void addAdmin(Customer customer) {

        try (Session session=sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            System.out.println("Inserted Done!!!");
        }
    }

    @Override
    public void deleteAdminById(int id) {
        try(Session session=sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query =session.createQuery("delete from Customer where customerId = :id");
            query.setParameter("id",id);
            query.executeUpdate();
            transaction.commit();
            System.out.println("Deleted Done!!!");
        }
    }

    @Override
    public void updateAdmin(int id, Customer customer) {
        try (Session session=sessionFactory.openSession()){
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
        }
    }

    @Override
    public Customer getCustomerById(int id) {
        Customer customer = null;
        try (Session session=sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Query<Customer> query = session.createQuery("FROM Customer WHERE customerId = :id" , Customer.class);
            session.setCacheMode(CacheMode.IGNORE);
            query.setParameter("id",id);
            customer = query.getSingleResult();
            transaction.commit();
        }catch (Exception e) {
            customer = null;
        }
        return customer;
    }
}
