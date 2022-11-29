package org.example.service;

import org.example.entity.Customer;
import org.example.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public List<Customer> showAllAdmins() {
        return adminRepository.showAllAdmins();
    }

    @Override
    public void addAdmin(Customer customer) {
        adminRepository.addAdmin(customer);
    }

    @Override
    public void deleteAdminById(int id) {
        adminRepository.deleteAdminById(id);
    }

    @Override
    public void updateAdmin(int id, Customer customer) {
        adminRepository.updateAdmin(id,customer);
    }
}
