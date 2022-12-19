package org.example.service;

import org.example.model.Customer;
import org.example.repository.LoginRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceImplTest {
    @Mock
    private LoginRepository loginRepository;
    @InjectMocks
    private LoginServiceImpl loginService;

    @Test
    public void findByEmail_ReturnsNull() throws Exception {
        //arrange
        when(loginRepository.findByEmail("test")).thenReturn(null);
        //act
        Customer c= loginService.findByEmail("test");
        //assert
        assertNull(c);
    }
    @Test
    public void findByEmail_ReturnsNotNull() throws Exception {
        //arrange
        Customer c=new Customer();
        c.setEmail("test");
        when(loginRepository.findByEmail("test")).thenReturn(c);
        //act
        Customer actuallyReturned= loginService.findByEmail("test");
        //assert
        assertEquals(c,actuallyReturned);
    }
    @Test
    public void findByEmail_ServiceThrowsException() throws Exception {
        //arrange
        Customer c=new Customer();
        c.setEmail("test");
        when(loginRepository.findByEmail("test")).thenThrow(new RuntimeException());
        //act
        //assert
        Assertions.assertThrows(Exception.class,()->loginService.findByEmail("test"));
    }

    @Test
    public void setLoggedInDoesnotThrowException() throws Exception {
        //arrange
        //act
        //assert
        loginService.setLoggedIn(1);
    }
    @Test
    public void setLoggedInThrowsException() throws Exception {
        //arrange
        doThrow(RuntimeException.class).when(loginRepository).setLoggedIn(1);
        //act
        //assert
        Assertions.assertThrows(Exception.class,()->loginService.setLoggedIn(1));
    }

    @Test
    public void setLoggedOutDoesnotThrowException() throws Exception {
        //arrange
        //act
        //assert
        loginService.setLoggedOut(1);
    }
    @Test
    public void setLoggedOutThrowsException() throws Exception {
        //arrange
        doThrow(RuntimeException.class).when(loginRepository).setLoggedOut(1);
        //act
        //assert
        Assertions.assertThrows(Exception.class,()->loginService.setLoggedOut(1));
    }

    @Test
    public void deActivateUserDoesnotThrowException() throws Exception {
        //arrange
        //act
        //assert
        loginService.deActivateUser(1);
    }
    @Test
    public void deActivateUserThrowsException() throws Exception {
        //arrange
        doThrow(RuntimeException.class).when(loginRepository).deActivateUser(1);
        //act
        //assert
        Assertions.assertThrows(Exception.class,()->loginService.deActivateUser(1));
    }

    @Test
    public void incrementWrongPasswordTrialsDoesnotThrowException() throws Exception {
        //arrange
        //act
        //assert
        loginService.incrementWrongPassTrials(1);
    }
    @Test
    public void incrementWrongPasswordTrialsThrowsException() throws Exception {
        //arrange
        doThrow(RuntimeException.class).when(loginRepository).incrementWrongPassTrials(1);
        //act
        //assert
        Assertions.assertThrows(Exception.class,()->loginService.incrementWrongPassTrials(1));
    }
}