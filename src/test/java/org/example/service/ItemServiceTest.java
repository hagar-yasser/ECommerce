package org.example.service;

import org.example.model.Item;
import org.example.repository.ItemRepository;
import org.example.repository.ItemRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        assertNotNull(expectedItemList);
        assertEquals(expectedItemList, actualItemsList);

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
        assertEquals(actualItem, expextedReturnItem);

        System.out.println(actualItem);
        System.out.println(expextedReturnItem);


    }

    @Test
    public void getItemByIdTest_whenInValidItemIDInput_ReturnException() throws Exception {
        when(sessionFactoryMock.openSession()).thenReturn(sessionMock);
        when(sessionMock.beginTransaction()).thenReturn(transactionMock);
        when(itemRepositoryMock.getItemById(anyInt(), any())).thenThrow(new RuntimeException());
//        itemService.getItemById(1);
//        verify(itemRepositoryMock, times(1)).getItemById(anyInt(), any());
        assertThrows(Exception.class, () -> itemService.getItemById(anyInt()));

    }

    @Test
    public void getItemsByNameTest_whenValidInputName_returnItemsList() throws Exception {

        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        item1.setItemId(1);
        item1.setName("Kia");
        item1.setCategory("car");
        item1.setPrice(50000);
        item1.setRating(4);

        Item item2 = new Item();
        item2.setItemId(2);
        item2.setName("ka");
        item2.setCategory("car");
        item2.setPrice(50000);
        item2.setRating(4);

        Item item3 = new Item();
        item3.setItemId(2);
        item3.setName("ka");
        item3.setCategory("car");
        item3.setPrice(50000);
        item3.setRating(4);
        items.add(item1);
        items.add(item2);
        items.add(item3);

        when(sessionFactoryMock.openSession()).thenReturn(sessionMock);
        when(sessionMock.beginTransaction()).thenReturn(transactionMock);
        when(itemRepositoryMock.getItemsByName(eq(item1.getName()), any())).thenReturn(items);

        List<Item> expectedItemsList = itemService.getItemsByName(item1.getName());
        assertEquals(items, expectedItemsList);
        System.out.println("actual " + items);
        System.out.println("expexted " + expectedItemsList);


    }

    @Test
    public void getItemsByNameTest_whenInValidInputName_returnException() {
        when(sessionFactoryMock.openSession()).thenReturn(sessionMock);
        when(sessionMock.beginTransaction()).thenReturn(transactionMock);
        when(itemRepositoryMock.getItemsByName(anyString(), any())).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> itemService.getItemsByName(anyString()));
    }


    @Test
    public void addItemTest_SendValidItem_thenSaveToDB_ReturnSavdItem() throws Exception {
        Item item = new Item();
        item.setItemId(1);
        item.setName("Kia");
        item.setCategory("car");
        item.setPrice(50000);
        item.setRating(4);

        when(sessionFactoryMock.openSession()).thenReturn(sessionMock);
        when(sessionMock.beginTransaction()).thenReturn(transactionMock);

        when(itemRepositoryMock.addItem(any(), any())).thenReturn(item);

        Item ExpectedReturnItem = itemService.addItem(item);

        assertEquals(ExpectedReturnItem, item);
        verify(itemRepositoryMock, times(1)).addItem(any(), any());


    }

    @Test
    public void addItemTest_SendNullItem_ReturnException() {
        when(sessionFactoryMock.openSession()).thenReturn(sessionMock);
        when(sessionMock.beginTransaction()).thenReturn(transactionMock);

        when(itemRepositoryMock.addItem(eq(null), any())).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> itemService.addItem(null));
    }

    @Test
    public void deleteItemTest_SendValidItemId_ThenDeleteItemFromDB() throws Exception {
        Item item = new Item();
        item.setItemId(1);
        item.setName("Kia");
        item.setCategory("car");
        item.setPrice(50000);
        item.setRating(4);
        when(sessionFactoryMock.openSession()).thenReturn(sessionMock);
        when(sessionMock.beginTransaction()).thenReturn(transactionMock);
        doNothing().when(itemRepositoryMock).deleteItem(any(), any());
        itemService.deleteItem(item.getItemId());
        verify(itemRepositoryMock, times(1)).deleteItem(any(), any());


    }

    @Test
    public void deleteItemTest_SendInvalidItemId_ThenThrowException() throws Exception {

//        when(sessionFactoryMock.openSession()).thenReturn(sessionMock);
//        when(sessionMock.beginTransaction()).thenReturn(transactionMock);
        doThrow(new RuntimeException()).when(itemRepositoryMock).deleteItem(any(), any());
        assertThrows(Exception.class, () -> itemService.deleteItem(anyInt()));


    }

    @Test
    public void updateItemTest_sendItem_ThenUpdateItem() throws Exception {
        Item item = new Item();
        item.setItemId(1);
        item.setName("Kia");
        item.setCategory("car");
        item.setPrice(50000);
        item.setRating(4);
        item.setName("Ford");
        when(sessionFactoryMock.openSession()).thenReturn(sessionMock);
        when(sessionMock.beginTransaction()).thenReturn(transactionMock);
        doNothing().when(itemRepositoryMock).updateItem(any(), any());

        itemService.updateItem(item);

        assertEquals("Ford", item.getName());
    }

    @Test
    public void updateItemTest_sendNull_ThenReturnException() throws Exception {

//        when(sessionFactoryMock.openSession()).thenReturn(sessionMock);
//        when(sessionMock.beginTransaction()).thenReturn(transactionMock);
        doThrow(new RuntimeException()).when(itemRepositoryMock).updateItem(eq(null), any());
        assertThrows(Exception.class, () -> itemService.updateItem(null));
    }


}
