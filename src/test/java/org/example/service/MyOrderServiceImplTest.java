package org.example.service;

import org.example.model.*;
import org.example.repository.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertEquals;
public class MyOrderServiceImplTest {
    private MyOrderRepository myOrderRepository;
    private SessionFactory sessionFactory;
    private CustomerItemRepositoryImpl customerItemRepository;
    private MyOrderItemRepositoryImpl myOrderItemRepository;
    private CustomerRepository customerRepository;

    private ItemRepository itemRepository;

    private Session session;
    private Transaction transaction;
    private MyOrderServiceImpl myOrderService;
    @Before
    public void init() {
        myOrderRepository= Mockito.mock(MyOrderRepositoryImpl.class);
        sessionFactory=Mockito.mock(SessionFactory.class);
        session =Mockito.mock(Session.class);
        transaction=Mockito.mock(Transaction.class);
        customerItemRepository=Mockito.mock(CustomerItemRepositoryImpl.class);
        myOrderItemRepository=Mockito.mock(MyOrderItemRepositoryImpl.class);
        customerRepository=Mockito.mock(CustomerRepositoryImpl.class);
        itemRepository=Mockito.mock(ItemRepositoryImpl.class);
        myOrderService=new MyOrderServiceImpl
                ( myOrderRepository,
                        sessionFactory,
                        customerItemRepository,
                        myOrderItemRepository,
                        customerRepository,  itemRepository);
    }
    @Test
    public void submitOrderTest_givenRepositoriesAndSessionFactory_CompletesWithoutThrowingExceptions() throws Exception {
        //Arrange
        LocalDate localDate = LocalDate.now();
        Item item1 = new Item();
        item1.setItemId(1);
        item1.setName("item1");
        item1.setQuantity(3);
        Item item2 = new Item();
        item2.setItemId(2);
        item2.setName("item2");
        item2.setQuantity(1);
        Customer customer = new Customer();
        customer.setFirstName("owner");
        MyOrder myOrder = new MyOrder();
        myOrder.setMyOrderDate(localDate);
        myOrder.setOwner(customer);
        CustomerItem customerItem1 = new CustomerItem();
        customerItem1.setCustomer(customer);
        customerItem1.setItem(item1);
        customerItem1.setQuantity(3);
        CustomerItem customerItem2 = new CustomerItem();
        customerItem2.setCustomer(customer);
        customerItem2.setItem(item2);
        customerItem2.setQuantity(1);
        List<CustomerItem> customerItems = new ArrayList<>();
        customerItems.add(customerItem1);
        customerItems.add(customerItem2);
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(customerItemRepository.getShoppingCartOfCustomer(1, session)).thenReturn(customerItems);
        doNothing().when(customerItemRepository).deleteShoppingCartOfCustomer(1, session);
        when(customerRepository.getCustomer(1, session)).thenReturn(customer);
        when(myOrderRepository.createNewMyOrder(any(MyOrder.class), any(Session.class))).thenReturn(myOrder);
        when(itemRepository.decrementItemQuantity(1, 3, session)).thenReturn(true);
        when(itemRepository.decrementItemQuantity(2, 1, session)).thenReturn(true);
        doNothing().when(myOrderItemRepository).addCustomerItemsInOrderItems(1, customerItems, session);
        doNothing().when(transaction).commit();
        //Act
        MyOrder returnedOrder = myOrderService.submitOrder(1);
        //Assert
        assertEquals(returnedOrder,myOrder);
    }
    @Test
    public void submitOrderTest_givenAnErrorWithHibernateCreatingSession_ThrowsException(){
       //Arrange
        when(sessionFactory.openSession()).thenThrow(new RuntimeException());

        //Act
        //Assert
        Assertions.assertThrows(Exception.class,()->myOrderService.submitOrder(1));

    }
    @Test
    public void showAllOrderTest_givenValidCustomerId_ReturnsAllOrders() throws Exception {
        //Arrange
        Customer owner=new Customer();
        owner.setCustomerId(1);
        owner.setFirstName("Owner");
        MyOrder myOrder=new MyOrder();
        myOrder.setOwner(owner);
        List<MyOrder>myOrders=new ArrayList<>();
        myOrders.add(myOrder);
        when(myOrderRepository.showAllOrder(1)).thenReturn(myOrders);
        //Act
        List<MyOrder>returnedOrders=myOrderService.showAllOrder(1);
        //Assert
        assertEquals(myOrders,returnedOrders);
    }
    @Test
    public void showAllOrderTest_givenCustomerIdWithNoOrders_ReturnsEmptyList() throws Exception {
        List<MyOrder>emptyList=new ArrayList<>();
        when(myOrderRepository.showAllOrder(1)).thenReturn(emptyList);
        //Act
        List<MyOrder>returnedOrders=myOrderService.showAllOrder(1);
        //Assert
        assertEquals(emptyList,returnedOrders);
    }
    @Test
    public void showAllOrderTest_givenHibernateException_ThrowsException(){
        when(myOrderRepository.showAllOrder(1)).thenThrow(new RuntimeException());
        Assertions.assertThrows(Exception.class,()->myOrderService.showAllOrder(1));
    }
    @Test
    public void showItemsForOrderTest_givenOrderIdHavingItems_ReturnsItemsInThatOrder() throws Exception {
        //Arrange
        LocalDate localDate=LocalDate.now();
        Item item1=new Item();
        item1.setName("item1");
        Item item2=new Item();
        item2.setName("item2");
        Customer customer=new Customer();
        customer.setFirstName("owner");
        MyOrder myOrder=new MyOrder();
        myOrder.setMyOrderDate(localDate);
        myOrder.setOwner(customer);
        myOrder.setMyOrderId(1);
        MyOrderItem myOrderItem1=new MyOrderItem();
        myOrderItem1.setItem(item1);
        myOrderItem1.setMyOrder(myOrder);
        MyOrderItem myOrderItem2=new MyOrderItem();
        myOrderItem2.setItem(item2);
        myOrderItem2.setMyOrder(myOrder);
        List<MyOrderItem>myOrderItems=new ArrayList<>();
        myOrderItems.add(myOrderItem1);
        myOrderItems.add(myOrderItem2);
        when(myOrderRepository.showItemsForOrder(1)).thenReturn(myOrderItems);
        //Act
        List<MyOrderItem>returnedMyOrderItems=myOrderService.showItemsForOrder(1);
        //Assert
        assertEquals(myOrderItems,returnedMyOrderItems);

    }
    @Test
    public void showItemsForOrderTest_givenOrderIdNotHavingItems_ReturnsEmptyList() throws Exception {
        //Arrange
        List<MyOrderItem>emptyList=new ArrayList<>();
        when(myOrderRepository.showItemsForOrder(1)).thenReturn(emptyList);
        //Act
        List<MyOrderItem>returnedMyOrderItems=myOrderService.showItemsForOrder(1);
        //Assert
        assertEquals(emptyList,returnedMyOrderItems);

    }
    @Test
    public void showItemsForOrderTest_givenHibernatException_ThrowsException(){
        when(myOrderRepository.showItemsForOrder(1)).thenThrow(new RuntimeException());
        Assertions.assertThrows(Exception.class,()->myOrderService.showItemsForOrder(1));
    }
}
