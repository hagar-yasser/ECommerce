package org.example;

import org.example.config.ShoppingServletConfig;
import org.example.config.WebConfig;
import org.example.model.Customer;
import org.example.model.CustomerItem;
import org.example.model.CustomerItemId;
import org.example.model.Item;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer();
        Item item= new Item();
        customer.setCustomerId(1);
        customer.setFirstName("aa");
        customer.setLastName("sss");

        item.setItemId(1);
        item.setCategory("food");
        item.setName("bread");
        item.setPrice(100);

        CustomerItem customerItem = new CustomerItem();
        CustomerItemId customerItemId = new CustomerItemId();
        customerItemId.setCustomer(customer);
        customerItemId.setItem(item);
        customerItem.setCustomerItemId(customerItemId);
        customerItem.setQuantity(100);


        System.out.println(customerItem.getCustomerItemId().getItem().getName());



    }
}