package org.example.service;

import org.example.model.CustomerItem;
import org.example.model.Item;

import java.util.List;

public interface CustomerItemService {

    public List<CustomerItem> getShoppingCartOfCustomer(int CustomerId);


    public boolean isItemInShoppingCart(int customerId, int itemId);

    //    public void increaseItemQuantity(List<CustomerItem> customerItems, Item item);
//    public void updateQuantityCustomerItem(int customerId, int itemId, int quantity);
    public void updateQuantityCustomerItem(int customerId, int itemId,int quantity);
    public void addToCustomerItem(int customerId, int itemId, int quantity);

    public void deleteFromCustomerItem(int customerId, int itemId);
    public void deleteCustomerItem(int customerId);

}
