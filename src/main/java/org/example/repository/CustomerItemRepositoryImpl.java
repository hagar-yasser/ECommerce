package org.example.repository;

import org.example.model.Customer;
import org.example.model.CustomerItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerItemRepositoryImpl implements CustomerItemRepository {

    @Override
<<<<<<< HEAD
    public List<CustomerItem> getShoppingCartOfCustomer(int customerId,Session session) {
        List<CustomerItem>shoppingCart=new ArrayList<>();
        Query query =session.createQuery("select ci from CustomerItem ci join ci.customerItemId.customer c where c.customerId = :customerId",CustomerItem.class);
        query.setParameter("customerId",customerId);
        shoppingCart=query.list();
=======
    public List<CustomerItem> getShoppingCartOfCustomer(int customerId, Session session) {
        List<CustomerItem> shoppingCart = new ArrayList<>();
        Query query = session.createQuery("select ci from CustomerItem ci join ci.customerItemId.customer c where c.customerId = :customerId", CustomerItem.class);
        query.setParameter("customerId", customerId);
        shoppingCart = query.list();
>>>>>>> 1886224aef4bbc91fc8de307c375c27c831ac724
        return shoppingCart;
    }

    @Override
<<<<<<< HEAD
    public void deleteShoppingCartOfCustomer(int customerId,Session session) {
        //getShoppingCart is always called before delete however the select is called again
        //I don't want to implement a single method that does both to not make them dependent on each other
        Query selectShoppingCart=session.createQuery("select ci from CustomerItem ci join ci.customerItemId.customer c where c.customerId = :customerId",CustomerItem.class);
        selectShoppingCart.setParameter("customerId",customerId);
        List<CustomerItem>shoppingCart=selectShoppingCart.list();
        for (CustomerItem ci:shoppingCart) {
=======
    public void deleteShoppingCartOfCustomer(int customerId, Session session) {
        //getShoppingCart is always called before delete however the select is called again
        //I don't want to implement a single method that does both to not make them dependent on each other
        Query selectShoppingCart = session.createQuery("select ci from CustomerItem ci join ci.customerItemId.customer c where c.customerId = :customerId", CustomerItem.class);
        selectShoppingCart.setParameter("customerId", customerId);
        List<CustomerItem> shoppingCart = selectShoppingCart.list();
        for (CustomerItem ci : shoppingCart) {
>>>>>>> 1886224aef4bbc91fc8de307c375c27c831ac724
            session.delete(ci);
        }
    }
    public void updateQuantityCustomerItem(int customerId, int itemId,int quantity , Session session) {

        try {
            Query query = session.createQuery("update CustomerItem ci set ci.quantity = :quantity where ci.customerItemId.customer.id = :cid AND ci.customerItemId.item.id = :itemId");
            query.setParameter("quantity", quantity);
            query.setParameter("cid", customerId);
            query.setParameter("itemId", itemId);
            query.executeUpdate();
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }



<<<<<<< HEAD

=======
>>>>>>> 1886224aef4bbc91fc8de307c375c27c831ac724
    @Override
    public void addItemToCustomerItem(CustomerItem customerItem, Session session) {
        session.save(customerItem);


    }




    @Override
    public void deleteItemFromCustomerItem(CustomerItem customerItem) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(CustomerItem.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query =session.createQuery("delete from CustomerItem ci where ci.customerItemId.customer.id = :cid AND ci.customerItemId.item.id = :itemId");
            query.setParameter("cid",customerItem.getCustomerItemId().getCustomer().getCustomerId());
            query.setParameter("itemId",customerItem.getItem().getItemId());
            query.executeUpdate();
            transaction.commit();
            System.out.println("Deleted Done!!!");
        }finally {
            sessionFactory.close();
            session.close();
        }
    }



    //    public void deleteItemFromCustomerItem(CustomerItem customerItem) {
//
//        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
//                .addAnnotatedClass(CustomerItem.class)
//                .buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        try {
//            Transaction transaction = session.beginTransaction();
//            session.delete(customerItem);
//            transaction.commit();
//        } finally {
//            sessionFactory.close();
//            session.close();
//        }
    public void deleteItemFromCustomerItem(CustomerItem customerItem, Session session) {

        session.delete(customerItem);

    }


////    @Override
//
////    public void addToCustomerItem(CustomerItem customerItem, Session session){
////
////                session.save(customerItem);
////
////
////
////    }
//    }customerItem
}
