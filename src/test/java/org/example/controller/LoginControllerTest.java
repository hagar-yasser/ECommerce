package org.example.controller;

import org.example.model.Customer;
import org.example.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {
    private static String loginPage="login";
    private static String loginPageRedirect="redirect:/shopping/login/login";
    private static String enterEmailPage="enterEmail";
    private static String allItemsPageRedirect="redirect:/shopping/items/all";
    private static String allItemsAdminPageRedirect="redirect:/shopping/admin/showAllItems/";
    @Mock
    private LoginService loginService;
    @Mock
    private ModelMap modelMap;
    @Mock
    private HttpSession httpSession;
    @InjectMocks
    private LoginController loginController;

    @Test
    public void loginTest_GetMapping_ReturnLoginPage() {
        //arrange
        //act
        String actuallyReturned=loginController.login();
        //assert
        assertEquals(loginPage,actuallyReturned);
    }

    @Test
    public void loginTest_PostMapping_NullCustomer_ReturnLoginPage() throws Exception {
        //arrange
        when(loginService.findByEmail("")).thenReturn(null);
        //act
        String actuallyReturned=loginController.login("","",httpSession,modelMap);
        //assert
        assertEquals(loginPage,actuallyReturned);
    }
    @Test
    public void loginTest_PostMapping_NotNullNotActivatedCustomer_ReturnEnterEmailPage() throws Exception {
        //arrange
        when(loginService.findByEmail("")).thenReturn(new Customer());
        //act
        String actuallyReturned=loginController.login("","",httpSession,modelMap);
        //assert
        assertEquals(enterEmailPage,actuallyReturned);
    }
    @Test
    public void loginTest_PostMapping_NotNullActivatedWrongPasswordWrongTrialsLessThan3Customer_ReturnLoginPage() throws Exception {
        //arrange
        Customer c= new Customer();
        c.setIsActivated(true);
        c.setPassword("password");
        c.setWrongPasswordTrials(2);
        when(loginService.findByEmail("")).thenReturn(c);
        //act
        String actuallyReturned=loginController.login("","wrong password",httpSession,modelMap);
        //assert
        assertEquals(loginPage,actuallyReturned);
    }
    @Test
    public void loginTest_PostMapping_NotNullActivatedWrongPasswordWrongTrialsEqual3Customer_ReturnEnterEmail() throws Exception {
        //arrange
        Customer c= new Customer();
        c.setIsActivated(true);
        c.setPassword("password");
        c.setWrongPasswordTrials(3);
        when(loginService.findByEmail("")).thenReturn(c);
        //act
        String actuallyReturned=loginController.login("","wrong password",httpSession,modelMap);
        //assert
        assertEquals(enterEmailPage,actuallyReturned);
    }
    @Test
    public void loginTest_PostMapping_NotNullActivatedCorrectPasswordNotAdminCustomer_ReturnAllItemsPageRedirect() throws Exception {
        //arrange
        Customer c= new Customer();
        c.setIsActivated(true);
        c.setPassword("password");
        when(loginService.findByEmail("")).thenReturn(c);
        //act
        String actuallyReturned=loginController.login("","password",httpSession,modelMap);
        //assert
        assertEquals(allItemsPageRedirect,actuallyReturned);
    }
    @Test
    public void loginTest_PostMapping_NotNullActivatedCorrectPasswordAdminCustomer_ReturnAllItemsAdminPageRedirect() throws Exception {
        //arrange
        Customer c= new Customer();
        c.setIsActivated(true);
        c.setPassword("password");
        c.setIsAdmin(true);
        when(loginService.findByEmail("")).thenReturn(c);
        //act
        String actuallyReturned=loginController.login("","password",httpSession,modelMap);
        //assert
        assertEquals(allItemsAdminPageRedirect,actuallyReturned);
    }
    @Test
    public void loginTest_PostMapping_ServiceThrowsException_ReturnLoginPage() throws Exception {
        //arrange
        when(loginService.findByEmail("")).thenThrow(new Exception());
        //act
        String actuallyReturned=loginController.login("","",httpSession,modelMap);
        //assert
        assertEquals(loginPage,actuallyReturned);
    }

    @Test
    public void logoutTest_NullCustomer_ReturnsLoginPageRedirect() {
        //arrange
        when(httpSession.getAttribute("customer")).thenReturn(null);
        //act
        String actualPage=loginController.logout(httpSession,modelMap);
        //assert
        assertEquals(loginPageRedirect,actualPage);
    }
    @Test
    public void logoutTest_NotNullCustomer_ReturnsLoginPageRedirect() {
        //arrange
        when(httpSession.getAttribute("customer")).thenReturn(new Customer());
        //act
        String actualPage=loginController.logout(httpSession,modelMap);
        //assert
        assertEquals(loginPageRedirect,actualPage);
    }
    @Test
    public void logoutTest_NotNullCustomerServiceThrowsException_ReturnsLoginPageRedirect() throws Exception {
        //arrange
        when(httpSession.getAttribute("customer")).thenReturn(new Customer());
        doThrow(new Exception()).when(loginService).setLoggedOut(anyInt());
        //act
        String actualPage=loginController.logout(httpSession,modelMap);
        //assert
        assertEquals(loginPage,actualPage);
    }
}