package org.example.service;

import org.example.model.Customer;
import org.example.model.CustomerItem;
import org.example.model.Item;
import org.example.repository.CustomerItemRepository;
import org.example.repository.CustomerRepository;
import org.example.repository.ItemRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerItemIServiceImplTest {
    @Mock
    SessionFactory sessionFactory;
    @Mock
    Session session;
    @Mock
    Transaction transaction;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerItemRepository customerItemRepository;
    @Mock
    private ItemRepository itemRepository;
    @InjectMocks
    CustomerItemIServiceImpl customerItemIService;


    @Test
    public void getShoppingCartOfCustomerTest_sessionOpensSuccessfully() {
        //arrange
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(customerItemRepository.getShoppingCartOfCustomer(1,session)).thenReturn(new ArrayList<>());
        //act
        List<CustomerItem> actuallyReturned=customerItemIService.getShoppingCartOfCustomer(1);
        //assert
        assertEquals(new ArrayList<>(),actuallyReturned);
    }
    @Test
    public void getShoppingCartOfCustomerTest_repositoryThrowsException() {
        //arrange
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(customerItemRepository.getShoppingCartOfCustomer(1,session)).thenThrow(new RuntimeException());
        //act
        List<CustomerItem> actuallyReturned=customerItemIService.getShoppingCartOfCustomer(1);
        //assert
        assertNull(actuallyReturned);
    }
    @Test
    public void isItemInShoppingCartTest_notInCart() {
        //arrange
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(customerItemRepository.getShoppingCartOfCustomer(1,session)).thenReturn(new ArrayList<>());
        //act
        boolean actuallyReturned=customerItemIService.isItemInShoppingCart(1,1);
        //assert
        assertFalse(actuallyReturned);
    }
    @Test
    public void isItemInShoppingCartTest_InCart() {
        //arrange
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        Customer c=new Customer();
        c.setCustomerId(1);
        List<CustomerItem>cart=generateAllValidCustomerCart(c);

        when(customerItemRepository.getShoppingCartOfCustomer(1,session)).thenReturn(cart);
        //act
        boolean actuallyReturned=customerItemIService.isItemInShoppingCart(1,1);
        //assert
        assertTrue(actuallyReturned);
    }

    @Test
    public void updateQuantityCustomerItem_add1ToExistingCustomerItem() throws Exception {
        //arrange
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        Customer c=new Customer();
        c.setCustomerId(1);
        List<CustomerItem>cart=generateAllValidCustomerCart(c);

        when(customerItemRepository.getShoppingCartOfCustomer(1,session)).thenReturn(cart);
        //act
        customerItemIService.updateQuantityCustomerItem(1,1,1);
        //assert
        //assert that the quantity of the first customerItem object increased by one
        //this only works because of object references
        assertEquals(3,cart.get(0).getQuantity());
    }
    @Test
    public void updateQuantityCustomerItem_customerItemRepositoryThrowsException() throws Exception {
        //arrange
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(customerItemRepository.getShoppingCartOfCustomer(1,session)).thenThrow(new RuntimeException());
        //act
        //assert
        Assertions.assertThrows(Exception.class,()->customerItemIService.updateQuantityCustomerItem(1,1,1));
    }

    @Test
    public void addToCustomerItemTest_completesSuccessfullyWithoutExceptions() throws Exception {
        //arrange
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        //act //assert
        customerItemIService.addToCustomerItem(1,1,1);
    }
    @Test
    public void addToCustomerItemTest_ThrowsException() throws Exception {
        //arrange
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(itemRepository.getItemById(1,session)).thenThrow(new RuntimeException());
        //act//assert
        Assertions.assertThrows(Exception.class,()->customerItemIService.addToCustomerItem(1,1,1));
    }
    @Test
    public void deleteFromCustomerItem_CompletesSuccessfully() throws Exception {
        //arrange
        //called in get shopping cart
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        Customer c=new Customer();
        c.setCustomerId(1);
        List<CustomerItem>cart=generateAllValidCustomerCart(c);

        when(customerItemRepository.getShoppingCartOfCustomer(1,session)).thenReturn(cart);
        //act
        customerItemIService.deleteFromCustomerItem(1,1);
    }
    @Test
    public void deleteFromCustomerItem_ThrowsException() throws Exception {
        //arrange
        //called in get shopping cart
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(customerItemRepository.getShoppingCartOfCustomer(1,session)).thenThrow(new RuntimeException());
        //act
        //assert
        Assertions.assertThrows(Exception.class,()->customerItemIService.deleteFromCustomerItem(1,1));
    }
    @Test
    public void deleteCustomerItemTest_completesSuccessfullyWithoutException() throws Exception {
        //arrange
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        //act//assert
        customerItemIService.deleteCustomerItem(1);
    }
    @Test
    public void deleteCustomerItemTest_ThrowsException() throws Exception {
        //arrange
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        doThrow(new RuntimeException()).when(customerItemRepository).deleteShoppingCartOfCustomer(1,session);
        //act//assert
        Assertions.assertThrows(Exception.class,()->customerItemIService.deleteCustomerItem(1));
    }
    public static List<CustomerItem>generateAllValidCustomerCart(Customer customer){
        Item item1 = new Item();
        item1.setItemId(1);
        item1.setName("item1");
        item1.setQuantity(3);
        Item item2 = new Item();
        item2.setItemId(2);
        item2.setName("item2");
        item2.setQuantity(1);
        CustomerItem customerItem1 = new CustomerItem();
        customerItem1.setCustomer(customer);
        customerItem1.setItem(item1);
        customerItem1.setQuantity(2);
        CustomerItem customerItem2 = new CustomerItem();
        customerItem2.setCustomer(customer);
        customerItem2.setItem(item2);
        customerItem2.setQuantity(1);
        List<CustomerItem> customerItems = new ArrayList<>();
        customerItems.add(customerItem1);
        customerItems.add(customerItem2);
        return customerItems;
    }
}