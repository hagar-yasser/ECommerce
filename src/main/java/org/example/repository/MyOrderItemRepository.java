package org.example.repository;

import org.example.model.CustomerItem;
import org.hibernate.Session;

import java.util.List;

public interface MyOrderItemRepository {
    public void addCustomerItemsInOrderItems(int orderId, List<CustomerItem> customerItems, Session session);
}
