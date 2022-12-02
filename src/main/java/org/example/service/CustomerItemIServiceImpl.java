package org.example.service;

import org.example.model.CustomerItem;
import org.example.model.Item;
import org.example.repository.CustomerItemRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerItemIServiceImpl implements CustomerItemService {


    private CustomerItemRepository customerItemRepository;
    private SessionFactory sessionFactory;

    CustomerItemIServiceImpl(CustomerItemRepository customerItemRepository) {
        this.customerItemRepository = customerItemRepository;
    }

    @Override
    public List<CustomerItem> getShoppingCartOfCustomer(int CustomerId) {
        List<CustomerItem> shoppingCart = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            shoppingCart = customerItemRepository.getShoppingCartOfCustomer(CustomerId, session);
            transaction.commit();
            return shoppingCart;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public void addItemToCart(Item item) {

    }

    @Override
    public boolean isItemInShoppingCart(List<CustomerItem> customerItems, Item item) {
        for (CustomerItem customerItem : customerItems) {
            if (customerItem.getCustomerItemId().getItem().equals(item))
                return true;
        }
        return false;
    }


//    public void addToCustomerItem(List<CustomerItem> customerItems, Item item) {
//        CustomerItem product = new CustomerItem();
//        for (CustomerItem customerItem : customerItems) {
//            if (customerItem.getCustomerItemId().getItem().equals(item)) {
//                product = customerItem;
//            }
//        }
//        if (isItemInShoppingCart(customerItems, item)) {
//
//        }
//
//
//    }
}
