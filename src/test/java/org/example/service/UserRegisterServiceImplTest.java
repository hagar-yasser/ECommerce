package org.example.service;

import org.example.model.Customer;
import org.example.model.VerificationToken;
import org.example.repository.UserRegisterRepository;
import org.example.repository.VerificationTokenRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserRegisterServiceImplTest {

    private UserRegisterServiceImpl userRegisterServiceImpl;
    private UserRegisterRepository userRegisterRepositoryMock;
    private VerificationTokenRepository verificationTokenRepositoryMock;

    public UserRegisterServiceImplTest() {
        userRegisterRepositoryMock = Mockito.mock(UserRegisterRepository.class);
        verificationTokenRepositoryMock = Mockito.mock(VerificationTokenRepository.class);
        userRegisterServiceImpl = new UserRegisterServiceImpl(userRegisterRepositoryMock,
                verificationTokenRepositoryMock);
    }


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void registerUserTest_acceptCustomerObject_thenSaveTheCustomer() {
        //Arrange
        Customer customer = new Customer();
        doNothing().when(userRegisterRepositoryMock).registerUser(any(Customer.class));
        //Act
         userRegisterServiceImpl.registerUser(customer);
        //Assert
        verify(userRegisterRepositoryMock, times(1)).registerUser(customer);

    }

    @Test
   public void findByEmailTest_acceptEmail_thenReturnCustomer() {
        //Arrange
        Customer customer = new Customer();
        String email = "anasroshdiii@gmail.com";
        when(userRegisterRepositoryMock.findByEmail(email)).thenReturn(customer);
        //Act
        customer = userRegisterServiceImpl.findByEmail(email);

        //Assert
        verify(userRegisterRepositoryMock, times(1)).findByEmail(email);

    }
}