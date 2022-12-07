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
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository;
    private SessionFactory sessionFactory;
    public ItemServiceImpl(ItemRepository itemRepository, SessionFactory sessionFactory){
        this.itemRepository=itemRepository;
        this.sessionFactory=sessionFactory;
    }
    @Override
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
    @Override
    public Item getItemById(int itemId) throws Exception {
        try(Session session=sessionFactory.openSession()){
            Transaction transaction=session.beginTransaction();
            Item item=itemRepository.getItemById(itemId,session);
            transaction.commit();
            return item;
        }
        catch (Exception e){
            throw new Exception("Couldn't get Items By Id from the database");
        }
    }
    @Override
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
    @Override
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
    @Override
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
    @Override
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

    @Override
    public List<Item> getItemsByNameCategoryRatingPrice
            (String name, String category, int rating, double price) throws Exception {
        List<Item> itemsByNameCategoryRatingPrice;
        try(Session session=sessionFactory.openSession()){
            Transaction transaction=session.beginTransaction();
            itemsByNameCategoryRatingPrice=itemRepository.getItemsByNameCategoryRatingPrice(name,category,rating,price,session);
            transaction.commit();
            return itemsByNameCategoryRatingPrice;
        }
        catch (Exception e){
            throw new Exception("Couldn't get Items through conjunction of search critereas from the database");
        }

    }



    @Override
    public Item addItem(Item item) throws Exception {
        try(Session session=sessionFactory.openSession()){
            Transaction transaction=session.beginTransaction();
            itemRepository.addItem(item,session);
            transaction.commit();
            return item;
        }
        catch (Exception e){
            throw new Exception("Couldn't add Item to database");
        }
    }
    @Override
    public void deleteItem(int itemId) throws Exception {
        try(Session session=sessionFactory.openSession()){
            Transaction transaction=session.beginTransaction();
            Item item=session.load(Item.class,itemId);
            itemRepository.deleteItem(item,session);
            transaction.commit();
        }
        catch (Exception e){
            throw new Exception("Couldn't remove Item from database");
        }
    }
    @Override
    public void updateItem(Item item) throws Exception {
        try(Session session=sessionFactory.openSession()){
            Transaction transaction=session.beginTransaction();
            itemRepository.updateItem(item,session);
            transaction.commit();
        }
        catch (Exception e){
            throw new Exception("Couldn't remove Item from database");
        }
    }
}
