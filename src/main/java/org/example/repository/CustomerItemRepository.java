package org.example.repository;

import org.example.model.CustomerItem;
import org.hibernate.Session;

import java.util.List;

public interface CustomerItemRepository {
    public List<CustomerItem> getShoppingCartOfCustomer(int customerId, Session session);
    public void deleteShoppingCartOfCustomer(int customerId, Session session);


//    void updateCustomerItemQuantity(int customerId, int itemId, Session session);
}
