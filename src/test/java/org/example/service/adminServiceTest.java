package org.example.service;

import org.example.model.Customer;
import org.example.repository.AdminRepositoryImpl;
import org.example.repository.MyOrderRepositoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class adminServiceTest {
    private AdminServiceImpl adminService;
    private AdminRepositoryImpl adminRepositoryMock;
    private MyOrderRepositoryImpl order;
    public adminServiceTest() {
        order = Mockito.mock(MyOrderRepositoryImpl.class);
        adminRepositoryMock = Mockito.mock(AdminRepositoryImpl.class);
        adminService = new AdminServiceImpl(adminRepositoryMock);
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void getAllAdminsTest_returnAllAdminsInCustomerEntity() throws Exception {
        //Arrange
        List<Customer> list = new ArrayList<>();
        Customer customer1 = new Customer(1,"rana", "ahmed","rana@gmail.com","pass",true,false,false,0);
        Customer customer2 = new Customer(2,"r", "ahmed","r@gmail.com","pass",true,false,false,0);
        list.add(customer1);
        list.add(customer2);
        when(adminRepositoryMock.showAllAdmins()).thenReturn(list);
        //Act
        List<Customer> returnedAdminList = adminService.showAllAdmins();
        //Assert
        assertNotNull(returnedAdminList);
        assertEquals(2, returnedAdminList.size());
        verify(adminRepositoryMock, times(1)).showAllAdmins();
    }
    @Test(expected= Exception.class)
    public void getAllAdminsTest_returnExceptionInCustomerEntity() throws Exception {
        when(adminRepositoryMock.showAllAdmins()).thenThrow(new RuntimeException("Couldn't get Items from the database"));
        adminService.showAllAdmins();
        verify(adminRepositoryMock, times(1)).showAllAdmins();
    }
    @Test
    public void deleteAdminByIdTest() throws Exception {
        doNothing().when(adminRepositoryMock).deleteAdminById(any(int.class));
        adminService.deleteAdminById(1);
        verify(adminRepositoryMock, times(1)).deleteAdminById(anyInt());
    }
    @Test(expected= Exception.class)
    public void deleteAdminByIdTest_returnExceptionInCustomerEntity() throws Exception {
        doNothing().doThrow(new Exception()).when(adminRepositoryMock).deleteAdminById(any(int.class));
        adminService.deleteAdminById(1);
        verify(adminRepositoryMock).deleteAdminById(1);
    }
    @Test
    public void addAdminTest() throws Exception {
        doNothing().when(adminRepositoryMock).addAdmin(any(Customer.class));
        Customer customer1 = new Customer(1,"rana", "ahmed","rana@gmail.com","pass",true,false,false,0);
        adminService.addAdmin(customer1);
        verify(adminRepositoryMock).addAdmin(any(Customer.class));
    }
    @Test(expected= Exception.class)
    public void addAdminTest_returnExceptionInCustomerEntity() throws Exception {
        doNothing().doThrow(new Exception("Couldn't add Items to the database")).when(adminRepositoryMock).addAdmin(any(Customer.class));
        Customer customer1 = new Customer(1,"rana", "ahmed","rana@gmail.com","pass",true,false,false,0);
        adminService.addAdmin(customer1);
        verify(adminRepositoryMock).addAdmin(any(Customer.class));
    }
    @Test
    public void updateAdminTest() throws Exception {
        doNothing().when(adminRepositoryMock).updateAdmin(anyInt(),any(Customer.class));
        Customer customer1 = new Customer(1,"rrrrrrr", "ahmed","rana@gmail.com","pass",true,false,false,0);
        adminService.updateAdmin(1,customer1);
        verify(adminRepositoryMock).updateAdmin(anyInt(),any(Customer.class));
    }
    @Test(expected= Exception.class)
    public void updateAdminTest_returnExceptionInCustomerEntity() throws Exception {
        doNothing().doThrow(new Exception("Couldn't update Items in the database")).when(adminRepositoryMock).updateAdmin(anyInt(),any(Customer.class));
        Customer customer1 = new Customer(1,"rrrrrrrrr", "ahmed","rana@gmail.com","pass",true,false,false,0);
        adminService.updateAdmin(1,customer1);
        verify(adminRepositoryMock).updateAdmin(anyInt(),any(Customer.class));
    }
    @Test
    public void getCustomerByIdTest() throws Exception {
        Customer customer1 = new Customer(1,"rana", "ahmed","rana@gmail.com","pass",true,false,false,0);
        when(adminRepositoryMock.getCustomerById(anyInt())).thenReturn(customer1);
        adminService.getCustomerById(1);
        verify(adminRepositoryMock, times(1)).getCustomerById(anyInt());
    }
    @Test(expected= Exception.class)
    public void getCustomerByIdTest_returnExceptionInCustomerEntity() throws Exception {
        when(adminRepositoryMock.getCustomerById(anyInt())).thenThrow(new RuntimeException());
        adminService.getCustomerById(1);
        verify(adminRepositoryMock, times(1)).getCustomerById(anyInt());
    }
}
