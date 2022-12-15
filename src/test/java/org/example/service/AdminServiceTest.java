package org.example.service;

import org.example.model.Customer;
import org.example.repository.AdminRepositoryImpl;
import org.example.repository.MyOrderRepositoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AdminServiceTest {
    private AdminServiceImpl adminService;
    private AdminRepositoryImpl adminRepositoryMock;

    public AdminServiceTest() {
        adminRepositoryMock = Mockito.mock(AdminRepositoryImpl.class);
        adminService = new AdminServiceImpl(adminRepositoryMock);
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test  // given when return
    public void getAllAdminsTest_returnAllAdminsInCustomerEntity() throws Exception {
        //Arrange
        List<Customer> list = new ArrayList<>();
        Customer customer1 = new Customer.CustomerBuilder(1,"rana", "ahmed","rana@gmail.com","pass",0)
                .setAdmin(true).build();
        Customer customer2 = new Customer.CustomerBuilder(1,"rana", "ahmed","rana@gmail.com","pass",0)
                .setAdmin(true).build();
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
    public void deleteAdminByIdTest_whenRecieveAdminId_thenDeleteItFromDb() throws Exception {
        doNothing().when(adminRepositoryMock).deleteAdminById(any(int.class));
        adminService.deleteAdminById(1);
        verify(adminRepositoryMock, times(1)).deleteAdminById(anyInt());
    }
    @Test(expected= Exception.class)
    public void deleteAdminByIdTest_returnExceptionInCustomerEntity() throws Exception {
        doThrow(new IllegalArgumentException()).when(adminRepositoryMock).deleteAdminById(any(int.class));
        adminService.deleteAdminById(1);
        verify(adminRepositoryMock).deleteAdminById(1);
    }
    @Test
    public void addAdminTest_whenAdminObject_thenSaveInDb() throws Exception {
        doNothing().when(adminRepositoryMock).addAdmin(any(Customer.class));
        Customer customer1 = new Customer.CustomerBuilder(1,"rana", "ahmed","rana@gmail.com","pass",0)
                .setAdmin(true).build();
        adminService.addAdmin(customer1);
        verify(adminRepositoryMock).addAdmin(any(Customer.class));
    }
    @Test(expected= Exception.class)
    public void addAdminTest_returnExceptionInCustomerEntity() throws Exception {
        doThrow(new IllegalArgumentException()).when(adminRepositoryMock).addAdmin(any(Customer.class));
        Customer customer1 = new Customer.CustomerBuilder(1,"rana", "ahmed","rana@gmail.com","pass",0)
                .setAdmin(true).build();        adminService.addAdmin(customer1);
        verify(adminRepositoryMock).addAdmin(any(Customer.class));
    }
    @Test
    public void updateAdminTest_takeIdAndCustomerObjectThatNeedToBeUpdated_thenUpdateById() throws Exception {
        doNothing().when(adminRepositoryMock).updateAdmin(anyInt(),any(Customer.class));
        Customer customer1 = new Customer.CustomerBuilder(1,"rana", "ahmed","rana@gmail.com","pass",0)
                .setAdmin(true).build();
        adminService.updateAdmin(1,customer1);
        verify(adminRepositoryMock).updateAdmin(anyInt(),any(Customer.class));
    }
    @Test(expected= Exception.class)
    public void updateAdminTest_returnExceptionInCustomerEntity() throws Exception {
        doThrow(new IllegalArgumentException("Couldn't update Items in the database")).when(adminRepositoryMock).updateAdmin(anyInt(),any(Customer.class));
        Customer customer1 = new Customer.CustomerBuilder(1,"rana", "ahmed","rana@gmail.com","pass",0)
                .setAdmin(true).build();
        adminService.updateAdmin(1,customer1);
        verify(adminRepositoryMock).updateAdmin(anyInt(),any(Customer.class));
    }
    @Test
    public void getCustomerByIdTest_takeIdOfTheCustomer_ThenGetCustomerById() throws Exception {
        Customer customer1 = new Customer.CustomerBuilder(1,"rana", "ahmed","rana@gmail.com","pass",0)
                .setAdmin(true).build();
        when(adminRepositoryMock.getCustomerById(anyInt())).thenReturn(customer1);
        adminService.getCustomerById(1);
        verify(adminRepositoryMock, times(1)).getCustomerById(anyInt());
    }
    @Test(expected= Exception.class)
    public void getCustomerByIdTest_returnExceptionInCustomerEntity() throws Exception {
        when(adminRepositoryMock.getCustomerById(anyInt())).thenThrow(new RuntimeException());
        adminService.getCustomerById(anyInt());
        verify(adminRepositoryMock, times(1)).getCustomerById(anyInt());
    }
}
