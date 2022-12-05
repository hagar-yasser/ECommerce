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

    public List<MyOrder> showAllOrder() {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM MyOrder ORDER BY MyOrderDate");
        List<MyOrder> myOrderList = query.getResultList();
        return myOrderList;
    }
}
