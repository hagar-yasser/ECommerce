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

<<<<<<< HEAD
    public Item getItemById (int itemId,Session session);
=======
    public Item getItemById(int ItemId, Session session);
>>>>>>> 1886224aef4bbc91fc8de307c375c27c831ac724

    public Item getItemById (int itemId,Session session);

}
