package org.example.service;

import org.example.model.CustomerItem;
import org.example.model.MyOrder;
import org.example.repository.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyOrderService {
    private MyOrderRepository myOrderRepository;
    private SessionFactory sessionFactory;
    private CustomerItemRepository customerItemRepository;
    private MyOrderItemRepository myOrderItemRepository;
    private CustomerRepository customerRepository;
    private ItemRepository itemRepository;

    public MyOrderService(MyOrderRepository myOrderRepository,
                          SessionFactory sessionFactory,
                          CustomerItemRepository customerItemRepository,
                          MyOrderItemRepository myOrderItemRepository,
                          CustomerRepository customerRepository,ItemRepository itemRepository) {
        this.myOrderRepository = myOrderRepository;
        this.sessionFactory = sessionFactory;
        this.customerItemRepository = customerItemRepository;
        this.myOrderItemRepository = myOrderItemRepository;
        this.customerRepository=customerRepository;
        this.itemRepository=itemRepository;
    }

    public MyOrder submitOrder(int customerId) throws Exception {
        MyOrder newOrder=new MyOrder();
        try(Session session=sessionFactory.openSession()){
            Transaction transaction=session.beginTransaction();
            List<CustomerItem> shoppingCart= customerItemRepository.getShoppingCartOfCustomer(customerId,session);
            if(shoppingCart!=null&&shoppingCart.size()>0) {
                customerItemRepository.deleteShoppingCartOfCustomer(customerId, session);
                newOrder.setOwner(customerRepository.getCustomer(customerId, session));
                newOrder.setMyOrderDate(LocalDate.now());
                newOrder = myOrderRepository.createNewMyOrder(newOrder, session);
                List<CustomerItem>validShoppingCart=new ArrayList<>();
                for (int i = 0; i < shoppingCart.size() ; i++) {
                    CustomerItem currItem=shoppingCart.get(i);
                    if(itemRepository.decrementItemQuantity(currItem.getItem().getItemId(),
                            currItem.getQuantity(),session)){
                        validShoppingCart.add(currItem);
                    }
                }
                myOrderItemRepository.addCustomerItemsInOrderItems(newOrder.getMyOrderId(), validShoppingCart, session);
            }
            transaction.commit();
            return newOrder;
        }
        catch (Exception e){
            throw new Exception("couldn't create an order with items in the shopping cart");
        }


    }
}
