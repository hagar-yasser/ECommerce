package org.example.service;

import org.example.model.Customer;
import org.example.model.CustomerItem;
import org.example.model.CustomerItemId;
import org.example.model.Item;
import org.example.repository.CustomerItemRepository;
import org.example.repository.CustomerRepository;
import org.example.repository.ItemRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerItemIServiceImpl implements CustomerItemService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerItemRepository customerItemRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private SessionFactory sessionFactory;


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
    public boolean isItemInShoppingCart(int customerId, int itemId) {
        List<CustomerItem> customerItems = getShoppingCartOfCustomer(customerId);
        for (CustomerItem customerItem : customerItems) {
            if ((customerItem.getCustomerItemId().getItem().getItemId()) == itemId)
                return true;
        }
        return false;
    }


    //    public void updateQuantityCustomerItem(int customerId, int itemId , int quantity) {
//
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        List<CustomerItem> customerItems = getShoppingCartOfCustomer(customerId);
//
////            CustomerItemId customerItemId = new CustomerItemId();
////            Customer customer = customerRepository.getCustomer(customerId, session);
////            Item item = itemRepository.getItemById(itemId,session);
////            customerItemId.setItem(item);
////            customerItemId.setCustomer(customer);
//        for (CustomerItem customerItem : customerItems) {
//            {
//                if ((customerItem.getCustomerItemId().getItem().getItemId()) == itemId) {
//                    customerItem.setQuantity(customerItem.getQuantity() + quantity);
//                    customerItemRepository.addItemToCustomerItem(customerItem, session);
//                    transaction.commit();
//
//                }
//            }
//        }
//    }
    public void updateQuantityCustomerItem(int customerId, int itemId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<CustomerItem> customerItems = getShoppingCartOfCustomer(customerId);

//
        for (CustomerItem customerItem : customerItems) {
            {
                if (isItemInShoppingCart(customerId,itemId)) {
                    customerItem.setQuantity(customerItem.getQuantity() + 1);
                    customerItemRepository.addItemToCustomerItem(customerItem, session);
                    transaction.commit();
                    break;

                }
            }

        }
    }



                public void addToCustomerItem ( int customerId, int itemId){
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                CustomerItem customerItem = new CustomerItem();
                CustomerItemId customerItemId = new CustomerItemId();
                Customer customer = customerRepository.getCustomer(customerId, session);
                Item item = itemRepository.getItemById(itemId, session);
                customerItemId.setItem(item);
                customerItemId.setCustomer(customer);
                customerItem.setCustomerItemId(customerItemId);
                customerItem.setQuantity(1);
                customerItemRepository.addItemToCustomerItem(customerItem, session);
                transaction.commit();


            }

                @Override
                public void deleteFromCustomerItem ( int customerId, int itemId){
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
//        CustomerItem customerItem= new CustomerItem();
//        CustomerItemId customerItemId = new CustomerItemId();
//        Customer customer = customerRepository.getCustomer(customerId, session);
//        Item item = itemRepository.getItemById(itemId,session);
//        customerItemId.setItem(item);
//        customerItemId.setCustomer(customer);
//        customerItem.setCustomerItemId(customerItemId);
                List<CustomerItem> customerItems = getShoppingCartOfCustomer(customerId);
                for (CustomerItem customerItem : customerItems) {
                    {
                        if ((customerItem.getCustomerItemId().getItem().getItemId()) == itemId) {
                            customerItemRepository.deleteItemFromCustomerItem(customerItem);
                            break;
                        }
                    }


                }
            }

                public void deleteCustomerItem ( int customerId){
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                customerItemRepository.deleteShoppingCartOfCustomer(customerId, session);
                transaction.commit();

            }
            }


