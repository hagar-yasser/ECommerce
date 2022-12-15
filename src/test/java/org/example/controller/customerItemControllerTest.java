package org.example.controller;

import org.example.model.Customer;
import org.example.model.CustomerItem;
import org.example.model.CustomerItemId;
import org.example.model.Item;
import org.example.service.CustomerItemIServiceImpl;
import org.example.service.CustomerItemService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class customerItemControllerTest {

    private CustomerItemController customerItemController;
    private CustomerItemService customerItemServiceMock;

    private HttpSession httpSessionMock;
    private Model modelMock;
    private final String loginPage = "login";
    private final String shoppingCartPage = "shoppingCart";

    private final String addItemPage = "addItem";
    private final String showAllItemsPage = "redirect:/shopping/items/all";

    @Before
    public void init() {
        this.httpSessionMock = mock(HttpSession.class);
        this.modelMock = mock(Model.class);
        this.customerItemServiceMock = mock(CustomerItemIServiceImpl.class);
        this.customerItemController = new CustomerItemController(customerItemServiceMock);
    }

    @Test
    public void showAllItemsInCartTest_givenNullCustomer_returnLoginPage() {

        when(httpSessionMock.getAttribute(anyString())).thenReturn(null);

        String expectedReturnPage = customerItemController.showAllItemsInCart(modelMock, httpSessionMock);

        assertEquals(loginPage, expectedReturnPage);


    }

    @Test
    public void showAllItemsInCartTest_givenValidCustomer_returnShoppingCartPage() {
        Item item = new Item();
        item.setItemId(1);
        item.setName("Kia");
        item.setCategory("car");
        Customer customer = new Customer();
        customer.setCustomerId(1);
        List<CustomerItem> shoppingCartList = new ArrayList<>();
        CustomerItemId customerItemId = new CustomerItemId();
        customerItemId.setCustomer(customer);
        customerItemId.setItem(item);
        CustomerItem customerItem1 = new CustomerItem();
        customerItem1.setCustomerItemId(customerItemId);
        shoppingCartList.add(customerItem1);


        when(httpSessionMock.getAttribute(anyString())).thenReturn(customer);

        when(customerItemServiceMock.getShoppingCartOfCustomer(anyInt())).thenReturn(shoppingCartList);

        String expectedReturnPage = customerItemController.showAllItemsInCart(modelMock, httpSessionMock);

        assertEquals(shoppingCartPage, expectedReturnPage);


    }

    @Test
    public void showAllItemsInCartTest_givenValidCustomer_throwException_thenReturnShoppingCartPage() {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(customer);
        when(customerItemServiceMock.getShoppingCartOfCustomer(anyInt())).thenThrow(RuntimeException.class);

        assertEquals(shoppingCartPage, customerItemController.showAllItemsInCart(modelMock, httpSessionMock));
    }

    @Test
    public void addItemToCartTest_givenNullCustomer_returnLoginPage() {
        when(httpSessionMock.getAttribute(anyString())).thenReturn(null);
        String expectedReturnPage = customerItemController.addItemToCart(1, 1, httpSessionMock, modelMock);

        assertEquals(loginPage, expectedReturnPage);


    }

    @Test
    public void addItemToCartTest_givenValidCustomerAndRepeatedItem_UpdateItemQuantityAndReturnShowAllItemsPage() {

        Customer customer = new Customer();
        customer.setCustomerId(1);


        when(httpSessionMock.getAttribute(anyString())).thenReturn(customer);
        when(customerItemServiceMock.isItemInShoppingCart(anyInt(), anyInt())).thenReturn(Boolean.TRUE);
        doNothing().when(customerItemServiceMock).updateQuantityCustomerItem(anyInt(), anyInt(), anyInt());

        String expectedReturnPage = customerItemController.addItemToCart(1, 1, httpSessionMock, modelMock);

        assertEquals(showAllItemsPage, expectedReturnPage);


    }

    @Test
    public void addItemToCartTest_givenValidCustomerAndNewItem_addItemToDB_thenReturnShowAllItemsPage() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1);

        when(httpSessionMock.getAttribute(anyString())).thenReturn(customer);
        when(customerItemServiceMock.isItemInShoppingCart(anyInt(), anyInt())).thenReturn(Boolean.FALSE);
        doNothing().when(customerItemServiceMock).addToCustomerItem(anyInt(), anyInt(), anyInt());
        String expectedReturnPage = customerItemController.addItemToCart(1, 1, httpSessionMock, modelMock);

        assertEquals(showAllItemsPage, expectedReturnPage);


    }

    @Test
    public void addItemToCartTest_givenValidCustomerAndNewItem_thenThrowExceptionAndReturnShowAddItemPage() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1);

        when(httpSessionMock.getAttribute(anyString())).thenReturn(customer);
        when(customerItemServiceMock.isItemInShoppingCart(anyInt(), anyInt())).thenReturn(Boolean.FALSE);
        doThrow(Exception.class).when(customerItemServiceMock).addToCustomerItem(anyInt(), anyInt(), anyInt());
        String expectedReturnPage = customerItemController.addItemToCart(1, 1, httpSessionMock, modelMock);

        assertEquals(addItemPage, expectedReturnPage);
    }

    @Test
    public void deleteItemFromCartTest_whenNullCustomer_thenReturnLoginPage() {

        when(httpSessionMock.getAttribute(anyString())).thenReturn(null);
        String expectedReturnPage = customerItemController.deleteItemFromCart(1, httpSessionMock, modelMock);


        assertEquals(loginPage, expectedReturnPage);
    }

    @Test
    public void deleteItemFromCartTest_whenValidCustomer_thenReturnShowItemsPage() {

        String showItemsPage = "redirect:/shopping/cart/showAll";
        Customer customer = new Customer();
        customer.setCustomerId(1);

        when(httpSessionMock.getAttribute(anyString())).thenReturn(customer);
        String expectedReturnPage = customerItemController.deleteItemFromCart(1, httpSessionMock, modelMock);

        assertEquals(showItemsPage, expectedReturnPage);
    }

    @Test
    public void deleteItemFromCartTest_whenValidCustomerAndValidItemId_throwsExpetion_thenReturnShoppingCartPage() {
        Customer customer = new Customer();
        customer.setCustomerId(1);

        when(httpSessionMock.getAttribute(anyString())).thenReturn(customer);
        doThrow(RuntimeException.class).when(customerItemServiceMock).deleteFromCustomerItem(1, 1);
        String expectedReturnPage = customerItemController.deleteItemFromCart(1, httpSessionMock, modelMock);

        assertEquals(shoppingCartPage, expectedReturnPage);

    }

    @Test
    public void deleteCustomerItem_whenNullCustomer_thenReturnLoginPage() {
        when(httpSessionMock.getAttribute(anyString())).thenReturn(null);

        String expextedReturnPage = customerItemController.deleteCustomerItem(httpSessionMock, modelMock);

        assertEquals(loginPage, expextedReturnPage);
    }

    @Test
    public void deleteCustomerItem_whenValidCustomer_thenReturnShowItemsPage() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        String showItemsPage = "redirect:/shopping/cart/showAll";

        when(httpSessionMock.getAttribute(anyString())).thenReturn(customer);
        doNothing().when(customerItemServiceMock).deleteCustomerItem(anyInt());

        String expextedReturnPage = customerItemController.deleteCustomerItem(httpSessionMock, modelMock);
        assertEquals(showItemsPage, expextedReturnPage);


    }

    @Test
    public void deleteCustomerItem_whenValidCustomer_throwException_thenReturnShoppingCartPage() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1);

        when(httpSessionMock.getAttribute(anyString())).thenReturn(customer);
        doThrow(Exception.class).when(customerItemServiceMock).deleteCustomerItem(anyInt());
        String expextedReturnPage = customerItemController.deleteCustomerItem(httpSessionMock, modelMock);

        assertEquals(shoppingCartPage,expextedReturnPage );

    }
}
  

