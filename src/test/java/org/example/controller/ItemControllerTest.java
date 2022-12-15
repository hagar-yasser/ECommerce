package org.example.controller;

import org.example.model.Customer;
import org.example.service.ItemService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {
    @Mock
    private ItemService itemService;

    @InjectMocks
    private ItemController itemController;
    @Mock
    private HttpSession httpSession;
    @Mock
    private Model model;
    private static String loginPage = "login";
    private static String searchItemsPage = "searchItems";
    private static String listItemsPage = "listItems";
    private static String listItemsAdminModulePage = "listItemsAdminModule";
    private static String listItemsAdminPage = "listItemsAdmin";

    @Test
    public void getSearchItemForm_givenNullCustomer_ReturnLogin() throws Exception {
        //arrange
        when(httpSession.getAttribute(anyString())).thenReturn(null);
        //act
        String jspPageResult = itemController.getSearchItemsForm(httpSession, model);
        //assert
        Assert.assertEquals(loginPage, jspPageResult);
    }

    @Test
    public void getSearchItemFrom_givenNotNullCustomer_ReturnSearchItemsPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setCustomerId(1);
        when(httpSession.getAttribute(anyString())).thenReturn(c);
        //act
        String jspPageResult = itemController.getSearchItemsForm(httpSession, model);
        //assert
        Assert.assertEquals(searchItemsPage, jspPageResult);
    }

    @Test
    public void getAllItems_givenNullCustomer_ReturnLogin() throws Exception {
        //arrange
        when(httpSession.getAttribute(anyString())).thenReturn(null);
        //act
        String jspPageResult = itemController.getAllItems(model, httpSession);
        //assert
        Assert.assertEquals(loginPage, jspPageResult);
    }

    @Test
    public void getAllItems_givenNotNullCustomer_ReturnListItemsPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setCustomerId(1);
        when(httpSession.getAttribute(anyString())).thenReturn(c);
        //act
        String jspPageResult = itemController.getAllItems(model, httpSession);
        //assert
        Assert.assertEquals(listItemsPage, jspPageResult);
    }

    @Test
    public void getAllItems_givenNotNullCustomerAndServiceThrowsException_ReturnListItemsPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setCustomerId(1);
        when(httpSession.getAttribute(anyString())).thenReturn(c);
        when(itemService.getAllItems()).thenThrow(new Exception());
        //act
        String jspPageResult = itemController.getAllItems(model, httpSession);
        //assert
        Assert.assertEquals(listItemsPage, jspPageResult);
    }

    @Test
    public void getAllItemsForAdmin_givenNullCustomer_ReturnLogin() {
        //arrange
        when(httpSession.getAttribute(anyString())).thenReturn(null);
        //act
        String jspPageResult = itemController.getAllItemsForAdmin(model, httpSession);
        //assert
        Assert.assertEquals(loginPage, jspPageResult);
    }

    @Test
    public void getAllItemsForAdmin_givenNotNullNotAdminCustomer_ReturnLogin() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setIsAdmin(false);
        when(httpSession.getAttribute(anyString())).thenReturn(c);
        //act
        String jspPageResult = itemController.getAllItemsForAdmin(model, httpSession);
        //assert
        Assert.assertEquals(loginPage, jspPageResult);
    }

    @Test
    public void getAllItemsForAdmin_givenNotNullAdminCustomer_ReturnListItemsAdminModule() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setIsAdmin(true);
        when(httpSession.getAttribute(anyString())).thenReturn(c);
        //act
        String jspPageResult = itemController.getAllItemsForAdmin(model, httpSession);
        //assert
        Assert.assertEquals(listItemsAdminModulePage, jspPageResult);
    }

    @Test
    public void getAllItemsForAdmin_givenNotNullAdminCustomerAndServiceThrowsException_ReturnListItemsAdminModule() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setIsAdmin(true);
        when(httpSession.getAttribute(anyString())).thenReturn(c);
        when(itemService.getAllItems()).thenThrow(new Exception());
        //act
        String jspPageResult = itemController.getAllItemsForAdmin(model, httpSession);
        //assert
        Assert.assertEquals(listItemsAdminModulePage, jspPageResult);
    }

    @Test
    public void getItemsByNameCategoryRatingPrice_givenNullCustomer_ReturnLogin() {
        //arrange
        when(httpSession.getAttribute(anyString())).thenReturn(null);
        //act
        String jspPageResult = itemController.getItemsByNameCategoryRatingPrice("", "", "", "", model, httpSession);
        //assert
        Assert.assertEquals(loginPage, jspPageResult);
    }

    @Test
    public void getItemsByNameCategoryRatingPrice_givenNotNullNotAdminCustomer_ReturnListItems() {
        //arrange
        Customer c = new Customer();
        when(httpSession.getAttribute(anyString())).thenReturn(c);
        //act
        String jspPageResult = itemController.getItemsByNameCategoryRatingPrice("", "", "", "", model, httpSession);
        //assert
        Assert.assertEquals(listItemsPage, jspPageResult);
    }

    @Test
    public void getItemsByNameCategoryRatingPrice_givenNotNullAdminCustomer_ReturnListItemsAdmin() {
        //arrange
        Customer c = new Customer();
        c.setIsAdmin(true);
        when(httpSession.getAttribute(anyString())).thenReturn(c);
        //act
        String jspPageResult = itemController.getItemsByNameCategoryRatingPrice("", "", "", "", model, httpSession);
        //assert
        Assert.assertEquals(listItemsAdminPage, jspPageResult);
    }

    @Test
    public void getItemsByNameCategoryRatingPrice_givenNotNullCustomerAndServiceThrowsException_ReturnSearchItems() throws Exception {
        //arrange
        Customer c = new Customer();
        when(httpSession.getAttribute(anyString())).thenReturn(c);
        when(itemService.getItemsByNameCategoryRatingPrice(anyString(), anyString(), anyInt(), anyDouble())).thenThrow(new Exception());
        //act
        String jspPageResult = itemController.getItemsByNameCategoryRatingPrice("", "", "", "", model, httpSession);
        //assert
        Assert.assertEquals(searchItemsPage, jspPageResult);
    }

    @Test
    public void getItemsByNameCategoryRatingPrice_givenNotNullCustomerAndNonProperPriceType_ReturnSearchItems() throws Exception {
        //arrange
        Customer c = new Customer();
        when(httpSession.getAttribute(anyString())).thenReturn(c);
        //act
        String jspPageResult = itemController.getItemsByNameCategoryRatingPrice("input", "", "", "", model, httpSession);
        //assert
        Assert.assertEquals(searchItemsPage, jspPageResult);
    }

    @Test
    public void getItemsByNameCategoryRatingPrice_givenNotNullCustomerAndNonProperRatingType_ReturnSearchItems() throws Exception {
        //arrange
        Customer c = new Customer();
        when(httpSession.getAttribute(anyString())).thenReturn(c);
        //act
        String jspPageResult = itemController.getItemsByNameCategoryRatingPrice("1.0", "", "", "input", model, httpSession);
        //assert
        Assert.assertEquals(searchItemsPage, jspPageResult);
    }
}