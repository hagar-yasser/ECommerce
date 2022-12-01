package org.example.repository;

import org.example.model.Item;
import org.hibernate.Session;

public interface ItemRepository {
    public Item addItem(Item item, Session session);
}
