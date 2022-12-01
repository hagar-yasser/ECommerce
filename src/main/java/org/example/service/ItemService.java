package org.example.service;

import org.example.model.Item;
import org.example.repository.ItemRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    private ItemRepository itemRepository;
    private SessionFactory sessionFactory;
    public ItemService(ItemRepository itemRepository,SessionFactory sessionFactory){
        this.itemRepository=itemRepository;
        this.sessionFactory=sessionFactory;
    }
    public List<Item> getAllItems() throws Exception {
        List<Item> allItems=new ArrayList<>();
        try(Session session=sessionFactory.openSession()){
            Transaction transaction=session.beginTransaction();
            allItems=itemRepository.getAllItems(session);
            transaction.commit();
            return allItems;
        }
        catch (Exception e){
            throw new Exception("Couldn't get Items from the database");
        }

    }
    public List<Item> getItemsByName(String name) throws Exception {
        List<Item> itemsByName=new ArrayList<>();
        try(Session session=sessionFactory.openSession()){
            Transaction transaction=session.beginTransaction();
            itemsByName=itemRepository.getItemsByName(name,session);
            transaction.commit();
            return itemsByName;
        }
        catch (Exception e){
            throw new Exception("Couldn't get Items By Name from the database");
        }


    }
    public List<Item> getItemsByCategory(String category) throws Exception {
        List<Item> itemsByCategory=new ArrayList<>();
        try(Session session=sessionFactory.openSession()){
            Transaction transaction=session.beginTransaction();
            itemsByCategory=itemRepository.getItemsByCategory(category,session);
            transaction.commit();
            return itemsByCategory;
        }
        catch (Exception e){
            throw new Exception("Couldn't get Items By Category from the database");
        }

    }
    public List<Item> getItemsByRating(int rating) throws Exception {
        List<Item> itemsByRating=new ArrayList<>();
        try(Session session=sessionFactory.openSession()){
            Transaction transaction=session.beginTransaction();
            itemsByRating=itemRepository.getItemsByRating(rating,session);
            transaction.commit();
            return itemsByRating;
        }
        catch (Exception e){
            throw new Exception("Couldn't get Items By Rating from the database");
        }

    }
    public List<Item> getItemsByPrice(double price) throws Exception {
        List<Item> itemsByPrice=new ArrayList<>();
        try(Session session=sessionFactory.openSession()){
            Transaction transaction=session.beginTransaction();
            itemsByPrice=itemRepository.getItemsByPrice(price,session);
            transaction.commit();
            return itemsByPrice;
        }
        catch (Exception e){
            throw new Exception("Couldn't get Items By Price from the database");
        }

    }
}
