package org.example.controller;

import org.example.model.Customer;
import org.example.service.MyOrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyOrderControllerTest {
    @Mock
    private MyOrderService myOrderService;
    @Mock
    private HttpSession httpSession;
    @Mock
    private Model model;
    @InjectMocks
    private MyOrderController myOrderController;
    private static String showAllOrdersPageRedirect ="redirect:/shopping/orders/showAllOrder";
    private static String showAllOrdersPage ="showAllOrder";
    private static String loginPageRedirect ="redirect:/shopping/login/login";
    private static String listAllOrderItemsPage="listAllOrderItems";
    @Test
    public void submitOrder_givenNotNullCustomer_RedirectToShowAllOrdersPage() throws Exception {
        //arrange
        Customer c=new Customer();
        c.setCustomerId(1);
        when(httpSession.getAttribute(anyString())).thenReturn(c);
        when(myOrderService.submitOrder(1)).thenReturn(null);
        //act
        String jspPageResult=myOrderController.submitOrder(model,httpSession);
        //assert
        Assert.assertEquals(showAllOrdersPageRedirect,jspPageResult);
    }
    @Test
    public void submitOrder_givenNullCustomer_RedirectToLogin() throws Exception {
        //arrange
        when(httpSession.getAttribute(anyString())).thenReturn(null);
        //act
        String jspPageResult=myOrderController.submitOrder(model,httpSession);
        //assert
        Assert.assertEquals(loginPageRedirect,jspPageResult);
    }
    @Test
    public void submitOrder_givenServiceThrowsException_RedirectToShowAllOrdersPageWithoutRedirect() throws Exception {
        //arrange
        Customer c=new Customer();
        c.setCustomerId(1);
        when(httpSession.getAttribute(anyString())).thenReturn(c);
        when(myOrderService.submitOrder(anyInt())).thenThrow(new Exception());
        //act
        String jspPageResult=myOrderController.submitOrder(model,httpSession);
        //assert
        Assert.assertEquals(showAllOrdersPage,jspPageResult);
    }

    @Test
    public void showAllOrder_givenNotNullCustomer_ReturnShowAllOrdersPage() throws Exception {
        //arrange
        Customer c=new Customer();
        c.setCustomerId(1);
        when(httpSession.getAttribute(anyString())).thenReturn(c);
        when(myOrderService.showAllOrder(1)).thenReturn(null);
        //act
        String jspPageResult=myOrderController.showAllOrder(model,httpSession);
        //assert
        Assert.assertEquals(showAllOrdersPage,jspPageResult);
    }

    @Test
    public void showAllOrder_givenNullCustomer_RedirectToLogin() throws Exception {
        //arrange
        when(httpSession.getAttribute(anyString())).thenReturn(null);
        //act
        String jspPageResult=myOrderController.showAllOrder(model,httpSession);
        //assert
        Assert.assertEquals(loginPageRedirect,jspPageResult);
    }
    @Test
    public void showAllOrder_givenServiceThrowsException_RedirectToShowAllOrdersPageWithoutRedirect() throws Exception {
        //arrange
        Customer c=new Customer();
        c.setCustomerId(1);
        when(httpSession.getAttribute(anyString())).thenReturn(c);
        when(myOrderService.showAllOrder(anyInt())).thenThrow(new Exception());
        //act
        String jspPageResult=myOrderController.showAllOrder(model,httpSession);
        //assert
        Assert.assertEquals(showAllOrdersPage,jspPageResult);
    }

    @Test
    public void showItemsForOrder_givenNotNullCustomer_ReturnListAllOrderItemsPage() throws Exception {
        //arrange
        Customer c=new Customer();
        c.setCustomerId(1);
        when(httpSession.getAttribute(anyString())).thenReturn(c);
        when(myOrderService.showItemsForOrder(1)).thenReturn(null);
        //act
        String jspPageResult=myOrderController.showItemForOrder(model,1,httpSession);
        //assert
        Assert.assertEquals(listAllOrderItemsPage,jspPageResult);
    }
    @Test
    public void showItemsForOrder_givenNullCustomer_RedirectToLogin() throws Exception {
        //arrange
        when(httpSession.getAttribute(anyString())).thenReturn(null);
        //act
        String jspPageResult=myOrderController.showItemForOrder(model,1,httpSession);
        //assert
        Assert.assertEquals(loginPageRedirect,jspPageResult);
    }
    @Test
    public void showItemsForOrder_givenServiceThrowsException_ReturnListAllOrderItemsPage() throws Exception {
        //arrange
        Customer c=new Customer();
        c.setCustomerId(1);
        when(httpSession.getAttribute(anyString())).thenReturn(c);
        when(myOrderService.showItemsForOrder(1)).thenThrow(new Exception());
        //act
        String jspPageResult=myOrderController.showItemForOrder(model,1,httpSession);
        //assert
        Assert.assertEquals(listAllOrderItemsPage,jspPageResult);
    }
}