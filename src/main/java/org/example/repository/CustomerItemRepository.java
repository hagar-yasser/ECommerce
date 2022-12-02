package org.example.repository;

import org.example.model.CustomerItem;
import org.hibernate.Session;

import java.util.List;

public interface CustomerItemRepository {
    public List<CustomerItem> getShoppingCartOfCustomer(int customerId, Session session);
    public void deleteShoppingCartOfCustomer(int customerId, Session session);
    public void addItemToCustomerItem(CustomerItem customerItem , Session session);
//    public void deleteItemFromCustomerItem(CustomerItem customerItem ,Session session);

    public void deleteItemFromCustomerItem(CustomerItem customerItem ) ;

//    void updateCustomerItemQuantity(int customerId, int itemId, Session session);
}
