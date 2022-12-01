package org.example.service;

import org.example.model.CustomerItem;
import org.example.model.MyOrder;
import org.example.repository.CustomerItemRepository;
import org.example.repository.CustomerRepository;
import org.example.repository.MyOrderItemRepository;
import org.example.repository.MyOrderRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyOrderService {
    private MyOrderRepository myOrderRepository;
    private SessionFactory sessionFactory;
    private CustomerItemRepository customerItemRepository;
    private MyOrderItemRepository myOrderItemRepository;
    private CustomerRepository customerRepository;

    public MyOrderService(MyOrderRepository myOrderRepository,
                          SessionFactory sessionFactory,
                          CustomerItemRepository customerItemRepository,
                          MyOrderItemRepository myOrderItemRepository,
                          CustomerRepository customerRepository) {
        this.myOrderRepository = myOrderRepository;
        this.sessionFactory = sessionFactory;
        this.customerItemRepository = customerItemRepository;
        this.myOrderItemRepository = myOrderItemRepository;
        this.customerRepository=customerRepository;
    }

    public MyOrder submitOrder(int customerId){
        MyOrder newOrder=new MyOrder();
        try(Session session=sessionFactory.openSession()){
            Transaction transaction=session.beginTransaction();
            List<CustomerItem> shoppingCart= customerItemRepository.getShoppingCartOfCustomer(customerId,session);
            if(shoppingCart!=null&&shoppingCart.size()>0) {
                customerItemRepository.deleteShoppingCartOfCustomer(customerId, session);
                newOrder.setOwner(customerRepository.getCustomer(customerId, session));
                newOrder = myOrderRepository.createNewMyOrder(newOrder, session);
                myOrderItemRepository.addCustomerItemsInOrderItems(newOrder.getMyOrderId(), shoppingCart, session);
            }
            transaction.commit();
        }

        return newOrder;
    }
}
