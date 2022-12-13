package org.example.repository;

import org.example.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MyOrderRepositoryImpl implements MyOrderRepository {
    private SessionFactory sessionFactory;

    public MyOrderRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public MyOrder createNewMyOrder(MyOrder myOrder, Session session) {
        session.save(myOrder);
        return myOrder;
    }

    @Override
    public List<MyOrder> showAllOrder(int customerId) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM MyOrder where owner_id = :owner_id ORDER BY MyOrderDate", MyOrder.class);
            query.setParameter("owner_id", customerId);
            List<MyOrder> myOrderList = query.list();
            return myOrderList;
        }
    }

    @Override
    public List<MyOrderItem> showItemsForOrder(int Orderid) {
        try (Session session = sessionFactory.openSession()) {
            List<MyOrderItem> items = new ArrayList<>();
            Query query = session.createQuery("select oi from MyOrderItem oi join oi.myOrderItemId.myOrder o where o.myOrderId = :orderId", MyOrderItem.class);
            query.setParameter("orderId", Orderid);
            items = query.list();
            return items;

        }

    }
}
