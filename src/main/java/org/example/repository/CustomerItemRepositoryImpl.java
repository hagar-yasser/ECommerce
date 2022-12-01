package org.example.repository;

import org.example.model.CustomerItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerItemRepositoryImpl implements CustomerItemRepository {

    @Override
    public List<CustomerItem> getShoppingCartOfCustomer(int customerId,Session session) {
        List<CustomerItem>shoppingCart=new ArrayList<>();
        Query query =session.createQuery("select ci from CustomerItem ci join ci.customerItemId.customer c where c.customerId = :customerId",CustomerItem.class);
        query.setParameter("customerId",customerId);
        shoppingCart=query.list();
        return shoppingCart;
    }

    @Override
    public void deleteShoppingCartOfCustomer(int customerId,Session session) {
        //getShoppingCart is always called before delete however the select is called again
        //I don't want to implement a single method that does both to not make them dependent on each other
        Query selectShoppingCart=session.createQuery("select ci from CustomerItem ci join ci.customerItemId.customer c where c.customerId = :customerId",CustomerItem.class);
        selectShoppingCart.setParameter("customerId",customerId);
        List<CustomerItem>shoppingCart=selectShoppingCart.list();
        for (CustomerItem ci:shoppingCart) {
            session.delete(ci);
        }
    }
}
