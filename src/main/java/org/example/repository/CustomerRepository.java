package org.example.repository;

import org.example.model.Customer;
import org.hibernate.Session;

public interface CustomerRepository {
    public Customer addCustomer(Customer customer, Session session);
    public Customer getCustomer(int customerId,Session session);
}
