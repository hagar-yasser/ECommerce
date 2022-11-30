package org.example;

import org.example.config.ShoppingServletConfig;
import org.example.config.WebConfig;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry=new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory=new MetadataSources(registry).buildMetadata().buildSessionFactory();

        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(ShoppingServletConfig.class);
        System.out.println("Hello world!");
    }
}