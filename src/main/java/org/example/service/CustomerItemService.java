package org.example.service;

import org.example.model.CustomerItem;
import org.example.model.Item;

import java.util.List;

public interface CustomerItemService {

    public List<CustomerItem> getShoppingCartOfCustomer(int CustomerId) ;

    public void addItemToCart(Item item);

    public boolean isItemInShoppingCart(List<CustomerItem> customerItems, Item item);
//    public void increaseItemQuantity(List<CustomerItem> customerItems, Item item);


}
