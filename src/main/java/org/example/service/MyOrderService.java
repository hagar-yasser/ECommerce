package org.example.service;

import org.example.model.MyOrder;

public interface MyOrderService {
    MyOrder submitOrder(int customerId) throws Exception;
}
