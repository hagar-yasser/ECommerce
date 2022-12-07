package org.example.repository;

import org.example.model.Customer;
import org.example.model.MyOrder;
import org.example.model.VerificationToken;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MyOrderRepositoryImpl implements MyOrderRepository{
    @Override
    public MyOrder createNewMyOrder(MyOrder myOrder,Session session) {
        session.save(myOrder);
        return myOrder;
    }

    @Override
    public List<MyOrder> showAllOrder(int customerId) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
//        Query query = session.createQuery("FROM MyOrder ORDER BY MyOrderDate");
        Query query = session.createQuery("SELECT i.item_id,i.category,i.name,i.price,i.quantity " +
                "MyOrder.myOrderDate \n" +
                "FROM item i" +
                "JOIN MyOrderItem ON i.item_id = MyOrderItem.item_id"+
                "JOIN MyOrder ON MyOrder.order_id = MyOrderItem.myOrder_id " +
                "Where MyOrder.order_id =: customerId");
        query.setParameter("customerId",customerId);
        List<MyOrder> myOrderList = query.getResultList();
        return myOrderList;
    }


}
