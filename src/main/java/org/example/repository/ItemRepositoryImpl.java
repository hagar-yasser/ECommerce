package org.example.repository;

import org.example.model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    @Override
    public Item addItem(Item item,Session session) {
        session.save(item);
        return item;
    }
}
