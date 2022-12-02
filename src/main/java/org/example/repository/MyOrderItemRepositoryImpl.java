package org.example.repository;

import org.example.model.CustomerItem;
import org.example.model.MyOrder;
import org.example.model.MyOrderItem;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Repository
public class MyOrderItemRepositoryImpl implements MyOrderItemRepository{
    @Override
    public void addCustomerItemsInOrderItems(int orderId, List<CustomerItem> customerItems, Session session) {
        MyOrder myOrder =session.load(MyOrder.class,orderId);
        Set<MyOrderItem>shoppingCartToMyOrderItems=new HashSet<>();
        for (CustomerItem ci:customerItems) {
            MyOrderItem moi=new MyOrderItem();
            moi.setItem(ci.getItem());
            moi.setMyOrder(myOrder);
            moi.setQuantity(ci.getQuantity());
            shoppingCartToMyOrderItems.add(moi);
        }
        myOrder.getMyOrderedItems().addAll(shoppingCartToMyOrderItems);
        session.persist(myOrder);
    }
}
