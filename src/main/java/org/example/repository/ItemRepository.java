package org.example.repository;

import org.example.model.Item;
import org.hibernate.Session;

import java.util.List;

public interface ItemRepository {
    public Item addItem(Item item, Session session);
    public void deleteItem(Item item ,Session session);
    public void updateItem(Item item, Session session);
    public List<Item>getAllItems(Session session);
    public List<Item>getItemsByName(String name,Session session);
    public List<Item>getItemsByCategory(String category,Session session);

    public List<Item>getItemsByRating(int rating,Session session);
    public List<Item>getItemsByPrice(double price,Session session);
    public Item getItemById(int itemId,Session session);
    public boolean decrementItemQuantity(int itemId,int decrementCounter,Session session);


    List<Item> getItemsByNameCategoryRatingPrice(String name, String category, int rating, double price, Session session);
}
