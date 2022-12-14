package org.example.service;

import org.example.model.Customer;
import org.example.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public List<Customer> showAllAdmins() throws Exception {
        try {
            return adminRepository.showAllAdmins();
        }catch (Exception e){
            throw new Exception("Couldn't get Items from the database");
        }
    }

    @Override
    public void addAdmin(Customer customer) throws Exception {
        try {
            adminRepository.addAdmin(customer);
        }catch (Exception e){
            throw new Exception("Couldn't get Items from the database");
        }
    }

    @Override
    public void deleteAdminById(int id) throws Exception {
        try {
            adminRepository.deleteAdminById(id);
        }catch (Exception e){
            throw new Exception("Couldn't get Items from the database");
        }
    }

    @Override
    public void updateAdmin(int id, Customer customer) throws Exception {
        try {
            adminRepository.updateAdmin(id, customer);
        }catch (Exception e){
            throw new Exception("Couldn't get Items from the database");
        }
    }
    public Customer getCustomerById(int id) throws Exception {
        try {
            return adminRepository.getCustomerById(id);
        }catch (Exception e){
            throw new Exception("Couldn't get Items from the database");
        }

    }
}
