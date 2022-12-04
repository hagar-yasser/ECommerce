package org.example;

import org.example.config.ShoppingServletConfig;
import org.example.config.WebConfig;
import org.example.model.Item;
import org.example.repository.ItemRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    private static byte[] readBytesFromFile(String filePath) throws IOException {
        File inputFile = new File(filePath);
        FileInputStream inputStream = new FileInputStream(inputFile);

        byte[] fileBytes = new byte[(int) inputFile.length()];
        inputStream.read(fileBytes);
        inputStream.close();

        return fileBytes;
    }
    public static void main(String[] args) throws IOException {
        StandardServiceRegistry registry=new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory=new MetadataSources(registry).buildMetadata().buildSessionFactory();
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(ShoppingServletConfig.class);
        System.out.println("Hello world!");
        ItemRepository itemRepository=applicationContext.getBean(ItemRepository.class);
        Item item=new Item();
        item.setCategory("category3");
        item.setName("name");
        item.setPrice(16.9);
        item.setQuantity(2);
        item.setRating(0);
        String photoFileName="/home/voidDev/Downloads/productPhoto.jpeg";
        byte[] photoBytes = readBytesFromFile(photoFileName);
        item.setImage(photoBytes);
        try(Session session=sessionFactory.openSession()){
            Transaction transaction=session.beginTransaction();
            itemRepository.addItem(item,session);
            transaction.commit();
        }

    }
}