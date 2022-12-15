package org.example.controller;

import org.example.model.Customer;
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
    @InjectMocks
    private AdminController adminController;
    private static String loginPage = "login";
    private static String adminListPage = "adminList";
    private static String addAdminPage = "addAdmin";
    private static String updateFormPage = "updateForm";

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
}