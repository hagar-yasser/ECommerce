package org.example.repository;

import org.example.model.CustomerItem;
import org.hibernate.Session;

import java.util.List;

public interface CustomerItemRepository {
    public List<CustomerItem> getShoppingCartOfCustomer(int customerId, Session session);
    public void deleteShoppingCartOfCustomer(int customerId, Session session);
    public void addItemToCustomerItem(CustomerItem customerItem , Session session);
//    public void deleteItemFromCustomerItem(CustomerItem customerItem ,Session session);
<<<<<<< HEAD
public void updateQuantityCustomerItem(int customerId, int itemId,int quantity , Session session);     public void deleteItemFromCustomerItem(CustomerItem customerItem ) ;
=======

    public void deleteItemFromCustomerItem(CustomerItem customerItem ) ;
>>>>>>> 1886224aef4bbc91fc8de307c375c27c831ac724

//    void updateCustomerItemQuantity(int customerId, int itemId, Session session);
}
