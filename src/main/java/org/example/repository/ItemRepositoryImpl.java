package org.example.repository;

import org.example.model.Customer;
import org.example.model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    @Override
    public Item addItem(Item item,Session session) {
        session.save(item);
        return item;
    }
    public void deleteItem(Item item,Session session){
        session.delete(item);
    }

    @Override
    public void updateItem(Item item, Session session) {
        Item item2=session.load(Item.class,item.getItemId());
        item2.setQuantity(item.getQuantity());
        item2.setName(item.getName());
        item2.setRating(item.getRating());
        item2.setImage(item.getImage());
        item2.setPrice(item.getPrice());
        item2.setCategory(item.getCategory());
        session.save(item2);
    }

    @Override
    public List<Item> getAllItems(Session session) {
        List<Item>allItems=new ArrayList<>();
        Query allItemsQuery=session.createQuery("from Item",Item.class);
        allItems=allItemsQuery.list();
        return allItems;
    }

    @Override
    public List<Item> getItemsByName(String name, Session session) {
        List<Item>itemsByName=new ArrayList<>();
        Query itemsByNameQuery=session.createQuery("from Item where name like :name",Item.class);
        itemsByNameQuery.setParameter("name","%"+name+"%");
        itemsByName=itemsByNameQuery.list();
        return itemsByName;
    }

    @Override
    public List<Item> getItemsByCategory(String category, Session session) {
        List<Item>itemsByCategory=new ArrayList<>();
        Query itemsByCategoryQuery=session.createQuery("from Item where category like :category",Item.class);
        itemsByCategoryQuery.setParameter("category","%"+category+"%");
        itemsByCategory=itemsByCategoryQuery.list();
        return itemsByCategory;
    }

    @Override
    public List<Item> getItemsByRating(int rating, Session session) {
        List<Item>itemsByRating=new ArrayList<>();
        Query itemsByRatingQuery=session.createQuery("from Item where rating = :rating",Item.class);
        itemsByRatingQuery.setParameter("rating",rating);
        itemsByRating=itemsByRatingQuery.list();
        return itemsByRating;
    }

    @Override
    public List<Item> getItemsByPrice(double price, Session session) {
        List<Item>itemsByPrice=new ArrayList<>();
        Query itemsByPriceQuery=session.createQuery("from Item where price = :price",Item.class);
        itemsByPriceQuery.setParameter("price",price);
        itemsByPrice=itemsByPriceQuery.list();
        return itemsByPrice;
    }
    @Override
    public List<Item> getItemsByNameCategoryRatingPrice
            (String name, String category, int rating, double price, Session session) {
        List<Item>itemsByNameCategoryRatingPrice;
        String queryConjunctionString="from Item";
        boolean queryHasWhereClause=false;
        if(price!=-1){
            if(!queryHasWhereClause){
                queryHasWhereClause=true;
                queryConjunctionString+=" where";
            }
            queryConjunctionString+=" price = :price";
        }
        if(rating!=-1){
            if(!queryHasWhereClause){
                queryHasWhereClause=true;
                queryConjunctionString+=" where";
            }
            else {
                queryConjunctionString+=" and";
            }
            queryConjunctionString+=" rating = :rating";
        }
        if(!name.equals("")){
            if(!queryHasWhereClause){
                queryHasWhereClause=true;
                queryConjunctionString+=" where";
            }
            else {
                queryConjunctionString+=" and";
            }
            queryConjunctionString+=" name like :name";
        }
        if(!category.equals("")){
            if(!queryHasWhereClause){
                queryHasWhereClause=true;
                queryConjunctionString+=" where";
            }
            else {
                queryConjunctionString+=" and";
            }
            queryConjunctionString+=" category like :category";
        }

        Query itemsByNameCategoryRatingPriceQuery=
                session.createQuery(queryConjunctionString,Item.class);
        if(price!=-1){
            itemsByNameCategoryRatingPriceQuery.setParameter("price",price);
        }
        if(rating!=-1){
            itemsByNameCategoryRatingPriceQuery.setParameter("rating",rating);
        }
        if(!name.equals("")){
            itemsByNameCategoryRatingPriceQuery.setParameter("name","%"+name+"%");
        }
        if(!category.equals("")){
            itemsByNameCategoryRatingPriceQuery.setParameter("category","%"+category+"%");
        }
        itemsByNameCategoryRatingPrice=itemsByNameCategoryRatingPriceQuery.list();
        return itemsByNameCategoryRatingPrice;
    }

    @Override
    public Item getItemById(int itemId,Session session) {
        Query getItemByIdQuery=session.createQuery("from Item where itemId = :itemId",Item.class);
        getItemByIdQuery.setParameter("itemId",itemId);
        return (Item)getItemByIdQuery.getSingleResult();
    }

    @Override
    public boolean decrementItemQuantity(int itemId, int decrementCounter,Session session) {
        Item item=getItemById(itemId,session);
        if(item.getQuantity()<decrementCounter){
            return false;
        }
        item.setQuantity(item.getQuantity()-decrementCounter);
        session.save(item);
        return true;

    }


}
