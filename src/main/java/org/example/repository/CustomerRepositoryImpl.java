package org.example.repository;

import org.example.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository{
    @Override
    public Customer addCustomer(Customer customer,Session session) {
        session.save(customer);
        return customer;
    }

    @Override
    public Customer getCustomer(int customerId,Session session) {

        return session.load(Customer.class,customerId);
    }
}
