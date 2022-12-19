package org.example.controller;

import org.example.model.Customer;
import org.example.model.VerificationToken;
import org.example.repository.VerificationTokenRepository;
import org.example.service.GmailSendEmail;
import org.example.service.UserRegisterServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class UserRegisterControllerTest {
    @InjectMocks
    private UserRegisterController userRegisterController;
    @Mock
    private UserRegisterServiceImpl userRegisterServiceImplMock;
    @Mock
    private VerificationTokenRepository verificationTokenRepositoryMock;
    @Mock
    private GmailSendEmail gmailSendEmailMock;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private Customer customer;
    @Mock
    private Model model;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
   public void testRegistration() throws Exception {
        String view = "register";
        String expectedView = userRegisterController.registration(model);
        Assert.assertEquals(view, expectedView);
    }


    @Test
    public void testAddUser_checkIfBindingError_ThenReturnToRegister() throws Exception {
        //Arrange
        when(bindingResult.hasErrors()).thenReturn(Boolean.valueOf("true"));
        //Act
        String view = userRegisterController.addUser(customer,bindingResult,model);
        //Assert
        Assert.assertEquals("register",view);

    }

    @Test
    public void testAddUser_checkIfCustomerInDB_ThenShowItIsRegistered() throws Exception {
        //Arrange
        when(userRegisterServiceImplMock.findByEmail(customer.getEmail())).thenReturn(customer);
        //Act
        String view = userRegisterController.addUser(customer,bindingResult,model);
        //Assert
        Assert.assertEquals("alreadyRegistered",view);

    }

    @Test
    public void testAddUser_ifCustomerNotInDB_ThenRegisterInDBAndCreateVerificationTokenAndSaveItDB() throws Exception {
        //Arrange
        VerificationToken verificationToken = new VerificationToken(customer);
        String subject = "This verification mail to complete registration process";
        String message = "Please Click on the link "+"http://localhost:8080/ECommerce/shopping/registration/verifyAccount?token="
                +verificationToken.getToken();
        when(userRegisterServiceImplMock.findByEmail(customer.getEmail())).thenReturn(null);
        doNothing().when(userRegisterServiceImplMock).registerUser(customer);
        //Act
        String view = userRegisterController.addUser(customer,bindingResult,model);
        //Assert
        Assert.assertEquals("verification",view);

    }
    @Test
    public void verifyUserAccount_acceptToken_thenActivateTheAccount() {
        //Arrange
        VerificationToken verificationToken = new VerificationToken(customer);
        when(verificationTokenRepositoryMock.findByVerificationToken(verificationToken.getToken())).thenReturn(verificationToken);
        when(userRegisterServiceImplMock.findByEmail(verificationToken.getUser().getEmail())).thenReturn(customer);
        doNothing().when(customer).setIsActivated(true);
        //Act
        String view = userRegisterController.verifyUserAccount(model,verificationToken.getToken());
        //Assert
        Assert.assertEquals("login",view);
    }

    @Test
    public void verifyUserAccount_acceptToken_thenIfTokenLinkBrokeClarifyToUser() {
        //Arrange
        VerificationToken verificationToken = new VerificationToken(customer);
        when(verificationTokenRepositoryMock.findByVerificationToken(verificationToken.getToken())).thenReturn(null);
        //Act
        String view = userRegisterController.verifyUserAccount(model,verificationToken.getToken());
        //Assert
        Assert.assertEquals("errorInVerification",view);

    }
}