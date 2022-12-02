package org.example.repository;

import org.example.model.MyOrder;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class MyOrderRepositoryImpl implements MyOrderRepository{
    @Override
    public MyOrder createNewMyOrder(MyOrder myOrder,Session session) {
        session.save(myOrder);
        return myOrder;
    }
}
