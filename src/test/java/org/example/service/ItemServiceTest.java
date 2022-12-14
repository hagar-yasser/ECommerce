package org.example.service;

import org.example.model.Item;
import org.example.repository.ItemRepository;
import org.example.repository.ItemRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ItemServiceTest {

    private ItemService itemService;
    private ItemRepository itemRepositoryMock;
    private SessionFactory sessionFactoryMock;
    private Session sessionMock;
    private Transaction transactionMock;


    @Before
    public void init() {
        this.itemRepositoryMock = mock(ItemRepositoryImpl.class);
        this.sessionFactoryMock = mock(SessionFactory.class);
        this.sessionMock = mock(Session.class);
        this.transactionMock = mock(Transaction.class);
        this.itemService = new ItemServiceImpl(itemRepositoryMock, sessionFactoryMock);


    }

    @Test
    public void getAllItemsTest_ReturnAllItems() throws Exception {

        Item item = new Item();
        item.setItemId(1);
        item.setName("Kia");
        item.setCategory("car");
        item.setPrice(50000);
        item.setRating(4);

        List<Item> actualItemsList = new ArrayList<>();
        actualItemsList.add(item);
        when(sessionFactoryMock.openSession()).thenReturn(sessionMock);
        when(sessionMock.beginTransaction()).thenReturn(transactionMock);
        when(itemRepositoryMock.getAllItems(any())).thenReturn(actualItemsList);
//        doThrow(new Exception()).when(itemRepositoryMock.getAllItems(any()));
        List<Item> expectedItemList = itemService.getAllItems();
//
        Assertions.assertEquals(expectedItemList, actualItemsList);
        System.out.println(expectedItemList);
        System.out.println(actualItemsList);

    }

    @Test
    public void getItemByIdTest_whenValidItemIDInput_ReturnItem() throws Exception {
        Item actualItem = new Item();
        actualItem.setItemId(1);
        actualItem.setName("Kia");
        actualItem.setCategory("car");
        actualItem.setPrice(50000);
        actualItem.setRating(4);
        when(sessionFactoryMock.openSession()).thenReturn(sessionMock);
        when(sessionMock.beginTransaction()).thenReturn(transactionMock);
        when(itemRepositoryMock.getItemById(anyInt(), any())).thenReturn(actualItem);
        Item expextedReturnItem = itemService.getItemById(actualItem.getItemId());
        Assertions.assertEquals(actualItem, expextedReturnItem);

        System.out.println(actualItem);
        System.out.println(expextedReturnItem);


    }

    @Test
    public void getItemByIdTest_whenInValidItemIDInput_ReturnException() throws Exception {
        when(sessionFactoryMock.openSession()).thenReturn(sessionMock);
        when(sessionMock.beginTransaction()).thenReturn(transactionMock);
        when(itemRepositoryMock.getItemById(anyInt(),any())).thenThrow (new RuntimeException());
        itemService.getItemById(1);
//        verify(itemRepositoryMock, times(1)).getItemById(anyInt(), any());
        Assertions.assertThrows(Exception.class, ()->  itemService.getItemById(anyInt()));

    }


}
