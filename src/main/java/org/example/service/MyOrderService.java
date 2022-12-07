package org.example.service;

import org.example.model.MyOrder;
import org.example.model.MyOrderItem;

import java.util.List;

public interface MyOrderService {
    MyOrder submitOrder(int customerId) throws Exception;
    List<MyOrderItem> showItemsForOrder(int orderId) throws Exception;
    List<MyOrder> showAllOrder(int customerId) throws Exception;
}
