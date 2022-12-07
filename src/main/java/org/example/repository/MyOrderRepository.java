package org.example.repository;

import org.example.model.CustomerItem;
import org.example.model.MyOrder;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import java.time.LocalDate;
import java.util.List;

public interface MyOrderRepository {
    public MyOrder createNewMyOrder(MyOrder myOrder, Session session);

    public List<MyOrder> showAllOrder(int customerId);
}
