package org.example.controller;

import org.example.model.Customer;
import org.example.model.Item;
import org.example.service.AdminService;
import org.example.service.ItemService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.meta.When;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {
    @Mock
    private AdminService adminServiceMock;
    @Mock
    private ItemService itemServiceMock;
    @Mock
    private HttpSession httpSessionMock;
    @Mock
    private BindingResult bindingResultMock;
    @Mock
    private Model modelMock;
    @Mock
    private MultipartFile imageMock;
    @InjectMocks
    private AdminController adminController;
    private static String loginPage = "login";
    private static String adminListPage = "adminList";
    private static String addAdminPage = "addAdmin";
    private static String updateFormPage = "updateForm";
    private static String addItemPage = "addItem";
    private static String listItemsAdminPage = "listItemsAdmin";
    private static String updateItemPage = "updateItem";

    //ShowAllAdmins
    @Test
    public void showAllAdminsTest_givenNullCustomer_ReturnLogin() {
        //arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(null);
        //act
        String result = adminController.showAllAdmins(modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void showAllAdminsTest_givenNotNullAndNotAdminCustomer_ReturnLogin() {
        //arrange
        Customer customer = new Customer();
        customer.setIsAdmin(false);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(customer);
        //act
        String result = adminController.showAllAdmins(modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void showAllAdminsTest_givenNotNullCustomerAndAdminCustomer_ReturnAdminList() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setIsAdmin(true);

        List<Customer> list = new ArrayList<>();
        Customer customer1 = new Customer.CustomerBuilder(1,"rana", "ahmed","rana@gmail.com","pass",0)
                .setAdmin(true).build();
        Customer customer2 = new Customer.CustomerBuilder(1,"rana", "ahmed","rana@gmail.com","pass",0)
                .setAdmin(true).build();
        list.add(customer1);
        list.add(customer2);

        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        when(adminServiceMock.showAllAdmins()).thenReturn(list);
        //act
        String result = adminController.showAllAdmins(modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(adminListPage, result);
    }
    @Test
    public void showAllAdminsTest_givenNotNullCustomerAndAdminCustomerAndThrowException_ReturnAdminList() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setIsAdmin(true);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        when(adminServiceMock.showAllAdmins()).thenThrow(new Exception());
        //act
        String result = adminController.showAllAdmins(modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(adminListPage, result);
    }

    //showFormForAdd
    @Test
    public void showFormForAddAdminTest_whenNullCustomer_ReturnLoginPage() {
        //arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(null);
        //act
        String result = adminController.showFormForAdd(modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void showFormForAddAdminTest_whenNotNullCustomerAndNotAdminCustomer_ReturnLogin() {
        //arrange
        Customer customer = new Customer();
        customer.setIsAdmin(false);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(customer);
        //act
        String result = adminController.showFormForAdd(modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void showFormForAddAdminTest_whenNotNullCustomerAndAdminCustomer_ReturnAddAdminPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setIsAdmin(true);

        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        //act
        String result = adminController.showFormForAdd(modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(addAdminPage, result);
    }
    @Test
    public void showFormForAddAdminTest_whenNotNullCustomerAndAdminCustomerAndThrowException_ReturnAddAdminPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setIsAdmin(true);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        when(modelMock.addAttribute(anyString(),any(Customer.class))).thenThrow(new RuntimeException());
        //act
        String result = adminController.showFormForAdd(modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(addAdminPage, result);
    }
    //addAdmin
    @Test
    public void addAdminTest_whenNullCustomer_ReturnLoginPage() {
        //arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(null);
        Customer c = new Customer();
        //act
        String result = adminController.addAdmin(c,bindingResultMock,modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void addAdminTest_whenNotNullCustomerAndNotAdminCustomer_ReturnLoginPage() {
        //arrange
        Customer customer = new Customer();
        customer.setIsAdmin(false);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(customer);
        //act
        String result = adminController.addAdmin(customer,bindingResultMock,modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void addAdminTest_whenNotNullCustomerAndAdminCustomer_AddCustomerAndReturnRedirectToShowAllAdminsPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setCustomerId(1);
        c.setIsAdmin(true);
        Customer customer = new Customer(); //to add
        customer.setCustomerId(2);

        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        doNothing().when(adminServiceMock).addAdmin(customer);
        //act
        String result = adminController.addAdmin(customer,bindingResultMock,modelMock,httpSessionMock);
        //assert
        Assert.assertEquals("redirect:/shopping/admin/showAllAdmins", result);
    }
    @Test
    public void addAdminTest_whenNotNullCustomerAndAdminCustomerAndThrowException_ReturnAddAdminPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setIsAdmin(true);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        doThrow(new Exception()).when(adminServiceMock).addAdmin(any(Customer.class));
        //act
        String result = adminController.addAdmin(c,bindingResultMock,modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(addAdminPage, result);
    }
    //deleteAdmin
    @Test
    public void deleteAdminTest_whenNullCustomer_ReturnLoginPage() {
        //arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(null);
        //act
        String result = adminController.deleteAdmin(1,modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void deleteAdminTest_whenNotNullCustomerAndNotAdminCustomer_ReturnLoginPage() {
        //arrange
        Customer customer = new Customer();
        customer.setIsAdmin(false);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(customer);
        //act
        String result = adminController.deleteAdmin(1,modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void deleteAdminTest_whenNotNullCustomerAndAdminCustomer_deleteCustomerAndReturnRedirectToShowAllAdminsPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setCustomerId(1);
        c.setIsAdmin(true);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        doNothing().when(adminServiceMock).deleteAdminById(1);
        //act
        String result = adminController.deleteAdmin(1,modelMock,httpSessionMock);
        //assert
        Assert.assertEquals("redirect:/shopping/admin/showAllAdmins", result);
    }
    @Test
    public void deleteAdminTest_whenNotNullCustomerAndAdminCustomerAndThrowException_ReturnAdminListPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setIsAdmin(true);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        doThrow(new Exception()).when(adminServiceMock).deleteAdminById(anyInt());
        //act
        String result = adminController.deleteAdmin(1,modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(adminListPage, result);
    }
    //showFormForUpdate(get)
    @Test
    public void showFormForUpdateTest_whenNullCustomer_ReturnLoginPage() {
        //arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(null);
        //act
        String result = adminController.showFormForUpdate(1,modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void showFormForUpdateTest_whenNotNullCustomerAndNotAdminCustomer_ReturnLoginPage() {
        //arrange
        Customer customer = new Customer();
        customer.setIsAdmin(false);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(customer);
        //act
        String result = adminController.showFormForUpdate(1,modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void showFormForUpdateTest_whenNotNullCustomerAndAdminCustomer_getCustomerByIdAndReturnUpdateFormPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setCustomerId(1);
        c.setIsAdmin(true);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        when(adminServiceMock.getCustomerById(anyInt())).thenReturn(c);
        //act
        String result = adminController.showFormForUpdate(1,modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(updateFormPage, result);
    }
    @Test
    public void showFormForUpdateTest_whenNotNullCustomerAndAdminCustomerAndThrowException_ReturnAdminListPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setIsAdmin(true);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        when(adminServiceMock.getCustomerById(1)).thenThrow(new Exception());
        //act
        String result = adminController.showFormForUpdate(1,modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(adminListPage, result);
    }
    //showFormForUpdate(post)
    @Test
    public void UpdateAdminTest_whenNullCustomer_ReturnLoginPage() {
        //arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(null);
        //act
        String result = adminController.showFormForUpdate(1,new Customer(),modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void UpdateAdminTest_whenNotNullCustomerAndNotAdminCustomer_ReturnLoginPage() {
        //arrange
        Customer customer = new Customer();
        customer.setIsAdmin(false);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(customer);
        //act
        String result = adminController.showFormForUpdate(1,new Customer(),modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void UpdateAdminTest_whenNotNullCustomerAndAdminCustomer_UpdateAdminAndReturnRedirectToShowAllAdminsListPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setCustomerId(1);
        c.setIsAdmin(true);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        doNothing().when(adminServiceMock).updateAdmin(anyInt(),any(Customer.class));
        //act
        String result = adminController.showFormForUpdate(1,new Customer(),modelMock,httpSessionMock);
        //assert
        Assert.assertEquals("redirect:/shopping/admin/showAllAdmins", result);
    }
    @Test
    public void UpdateAdminTest_whenNotNullCustomerAndAdminCustomerAndThrowException_ReturnUpdateFormPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setIsAdmin(true);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        doThrow(new Exception()).when(adminServiceMock).updateAdmin(anyInt(),any(Customer.class));
        //act
        String result = adminController.showFormForUpdate(1,new Customer(),modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(updateFormPage, result);
    }
    //getAddItem
    @Test
    public void getAddItemTest_whenNullCustomer_ReturnLoginPage() {
        //arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(null);
        //act
        String result = adminController.getAddItem(httpSessionMock,modelMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void getAddItemTest_whenNotNullCustomerAndNotAdminCustomer_ReturnLogin() {
        //arrange
        Customer customer = new Customer();
        customer.setIsAdmin(false);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(customer);
        //act
        String result = adminController.getAddItem(httpSessionMock,modelMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void getAddItemTest_whenNotNullCustomerAndAdminCustomer_ReturnAddItemPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setIsAdmin(true);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        //act
        String result = adminController.getAddItem(httpSessionMock,modelMock);
        //assert
        Assert.assertEquals(addItemPage, result);
    }
    @Test
    public void getAddItemTest_whenNotNullCustomerAndAdminCustomerAndThrowException_ReturnAddItemPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setIsAdmin(true);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        when(modelMock.addAttribute(anyString(),any(Item.class))).thenThrow(new RuntimeException());
        //act
        String result = adminController.getAddItem(httpSessionMock,modelMock);
        //assert
        Assert.assertEquals(addItemPage, result);
    }
    //addItem
    @Test
    public void addItemTest_whenNullCustomer_ReturnLoginPage() {
        //arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(null);
        //act
        String result = adminController.addItem(httpSessionMock,new Item(),bindingResultMock
                ,imageMock,modelMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void addItemTest_whenNotNullCustomerAndNotAdminCustomer_ReturnLoginPage() {
        //arrange
        Customer customer = new Customer();
        customer.setIsAdmin(false);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(customer);
        //act
        String result = adminController.addItem(httpSessionMock,new Item(),bindingResultMock
                ,imageMock,modelMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void addItemTest_whenNotNullCustomerAndAdminCustomer_AddItemAndReturnRedirectToShowAllItemsPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setCustomerId(1);
        c.setIsAdmin(true);
        Item item = new Item();
        item.setItemId(1);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        when(itemServiceMock.addItem(item)).thenReturn(item);
        when(imageMock.isEmpty()).thenReturn(false);
        //act
        String result = adminController.addItem(httpSessionMock,item,bindingResultMock
                ,imageMock,modelMock);
        //assert
        Assert.assertEquals("redirect:/shopping/admin/showAllItems/", result);
    }
    @Test
    public void addItemTest_whenNotNullCustomerAndAdminCustomerAndThrowException_ReturnAdditemPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setIsAdmin(true);
        Item item = new Item();
        item.setItemId(1);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        when(itemServiceMock.addItem(item)).thenThrow(new Exception());
        //act
        String result = adminController.addItem(httpSessionMock,item,bindingResultMock
                ,imageMock,modelMock);        //assert
        Assert.assertEquals(addItemPage, result);
    }
    //showAllItems
    @Test
    public void showAllItemsTest_whenNullCustomer_ReturnLogin() throws Exception {
        //arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(null);
        //act
        String result = adminController.showAllItems(httpSessionMock,modelMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void showAllItemsTest_whenNotNullAndNotAdminCustomer_ReturnLogin() throws Exception {
        //arrange
        Customer customer = new Customer();
        customer.setIsAdmin(false);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(customer);
        //act
        String result = adminController.showAllItems(httpSessionMock,modelMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void showAllItemsTest_whenNotNullCustomerAndAdminCustomer_ReturnItemListPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setIsAdmin(true);

        List<Item> list = new ArrayList<>();
        Item item = new Item();
        item.setItemId(1);
        item.setName("Kia");
        item.setCategory("car");
        item.setPrice(50000);
        item.setRating(4);
        list.add(item);

        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        when(itemServiceMock.getAllItems()).thenReturn(list);
        //act
        String result = adminController.showAllItems(httpSessionMock,modelMock);
        //assert
        Assert.assertEquals(listItemsAdminPage, result);
    }
    @Test
    public void showAllItemsTest_whenNotNullCustomerAndAdminCustomerAndThrowException_ReturnItemListPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setIsAdmin(true);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        when(itemServiceMock.getAllItems()).thenThrow(new Exception());
        //act
        String result = adminController.showAllItems(httpSessionMock,modelMock);
        //assert
        Assert.assertEquals(listItemsAdminPage, result);
    }
    //getUpdateItemForm
    @Test
    public void getUpdateItemFormTest_whenNullCustomer_ReturnLoginPage() throws Exception {
        //arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(null);
        //act
        String result = adminController.getUpdateItemForm(1,httpSessionMock,modelMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void getUpdateItemFormTest_whenNotNullCustomerAndNotAdminCustomer_ReturnLoginPage() throws Exception {
        //arrange
        Customer customer = new Customer();
        customer.setIsAdmin(false);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(customer);
        //act
        String result = adminController.getUpdateItemForm(1,httpSessionMock,modelMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void getUpdateItemFormTest_whenNotNullCustomerAndAdminCustomer_getItemByIdAndReturnUpdateItemPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setCustomerId(1);
        c.setIsAdmin(true);
        Item item =new Item();
        item.setItemId(1);
        item.setImage(new byte[0]);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        when(itemServiceMock.getItemById(anyInt())).thenReturn(item);
        //act
        String result = adminController.getUpdateItemForm(1,httpSessionMock,modelMock);
        //assert
        Assert.assertEquals(updateItemPage, result);
    }
    @Test
    public void getUpdateItemFormTest_whenNotNullCustomerAndAdminCustomerAndThrowException_ReturnListItemsAdminPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setIsAdmin(true);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        when(itemServiceMock.getItemById(anyInt())).thenThrow(new Exception());
        //act
        String result = adminController.getUpdateItemForm(1,httpSessionMock,modelMock);
        //assert
        Assert.assertEquals(listItemsAdminPage, result);
    }
    //updateItem
    @Test
    public void updateItemTest_whenNullCustomer_ReturnLoginPage() {
        //arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(null);
        //act
        String result = adminController.updateItem(httpSessionMock,new Item(),bindingResultMock,imageMock,null,modelMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void updateItemTest_whenNotNullCustomerAndNotAdminCustomer_ReturnLoginPage() {
        //arrange
        Customer customer = new Customer();
        customer.setIsAdmin(false);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(customer);
        //act
        String result = adminController.updateItem(httpSessionMock,new Item(),bindingResultMock,imageMock,null,modelMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void updateItemTest_whenNotNullCustomerAndAdminCustomerAndNotEmptyImage_UpdateItemAndReturnRedirectToShowAllItemsListPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setCustomerId(1);
        c.setIsAdmin(true);
        Item item =new Item();
        item.setItemId(1);
        item.setImage(new byte[0]);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        doNothing().when(itemServiceMock).updateItem(any(Item.class));
        when(imageMock.isEmpty()).thenReturn(false);
        //act
        String result = adminController.updateItem(httpSessionMock,item,bindingResultMock,imageMock,null,modelMock);
        //assert
        Assert.assertEquals("redirect:/shopping/admin/showAllItems/", result);
    }
    @Test
    public void updateItemTest_whenNotNullCustomerAndAdminCustomerAndEmptyImageAndDeleteImageNotEqualNull_UpdateItemAndReturnRedirectToShowAllItemsListPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setCustomerId(1);
        c.setIsAdmin(true);
        Item item =new Item();
        item.setItemId(1);
        String deleteImage = "1";
        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        doNothing().when(itemServiceMock).updateItem(any(Item.class));
        when(imageMock.isEmpty()).thenReturn(true);
        //act
        String result = adminController.updateItem(httpSessionMock,item,bindingResultMock,imageMock,deleteImage,modelMock);
        //assert
        Assert.assertEquals("redirect:/shopping/admin/showAllItems/", result);
    }
    @Test
    public void updateItemTest_whenNotNullCustomerAndAdminCustomerAndEmptyImageAndDeleteImageEqualNull_UpdateItemAndReturnRedirectToShowAllItemsListPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setCustomerId(1);
        c.setIsAdmin(true);
        Item item =new Item();
        item.setItemId(1);
        String deleteImage = "null";
        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        when(imageMock.isEmpty()).thenReturn(true);
        doNothing().when(itemServiceMock).updateItem(any(Item.class));
        when(itemServiceMock.getItemById(anyInt())).thenReturn(item);
        //act
        String result = adminController.updateItem(httpSessionMock,item,bindingResultMock,imageMock,deleteImage,modelMock);
        //assert
        Assert.assertEquals("redirect:/shopping/admin/showAllItems/", result);
    }
    @Test
    public void updateItemTest_whenNotNullCustomerAndAdminCustomerAndThrowException_ReturnUpdateItemPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setIsAdmin(true);
        Item item =new Item();
        item.setItemId(1);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        doThrow(new Exception()).when(itemServiceMock).updateItem(any(Item.class));
        //act
        String result = adminController.updateItem(httpSessionMock,item,bindingResultMock,imageMock,null,modelMock);
        //assert
        Assert.assertEquals(updateItemPage, result);
    }
    //deleteItem
    @Test
    public void deleteItemTest_whenNullCustomer_ReturnLoginPage() {
        //arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(null);
        //act
        String result = adminController.deleteItem(1,modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void deleteItemTest_whenNotNullCustomerAndNotAdminCustomer_ReturnLoginPage() {
        //arrange
        Customer customer = new Customer();
        customer.setIsAdmin(false);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(customer);
        //act
        String result = adminController.deleteItem(1,modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(loginPage, result);
    }
    @Test
    public void deleteItemTest_whenNotNullCustomerAndAdminCustomer_deleteItemAndReturnRedirectToShowAllItemsPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setCustomerId(1);
        c.setIsAdmin(true);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        doNothing().when(itemServiceMock).deleteItem(1);
        //act
        String result = adminController.deleteItem(1,modelMock,httpSessionMock);
        //assert
        Assert.assertEquals("redirect:/shopping/admin/showAllItems/", result);
    }
    @Test
    public void deleteItemTest_whenNotNullCustomerAndAdminCustomerAndThrowException_ReturnListItemsAdminPage() throws Exception {
        //arrange
        Customer c = new Customer();
        c.setIsAdmin(true);
        when(httpSessionMock.getAttribute(anyString())).thenReturn(c);
        doThrow(new Exception()).when(itemServiceMock).deleteItem(anyInt());
        //act
        String result = adminController.deleteItem(1,modelMock,httpSessionMock);
        //assert
        Assert.assertEquals(listItemsAdminPage, result);
    }
}