package org.example.controller;


import org.example.model.Customer;
import org.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @GetMapping("/showAllAdmins")
    public String showAllAdmins(Model model) {
        List<Customer> listOfAdmins = adminService.showAllAdmins();
        model.addAttribute("admins", listOfAdmins);
        return "adminList";  //the view that shows all the admins
    }

    @GetMapping("/addAdmin")
    public String showFormForAdd(Model model) {
        Customer customer = new Customer();
        customer.setIsAdmin(true);
        model.addAttribute("admin", customer);
        return "addAdmin"; //the view that has the form to add admin
    }

    @RequestMapping(value = "addAdmin", method = RequestMethod.POST)
    public String addAdmin(@ModelAttribute("admin") Customer admin) {
        admin.setIsAdmin(true);
        adminService.addAdmin(admin);
        return "redirect:/shopping/admin/showAllAdmins";
    }

    @RequestMapping(value="deleteAdmin/{id}",method = RequestMethod.GET)
    public String deleteAdmin(@PathVariable int id){
        adminService.deleteAdminById(id);
        return "redirect:/shopping/admin/showAllAdmins";
    }

    //not completed
    @RequestMapping(value="updateForm/{id}",method = RequestMethod.GET)
    public String showFormForUpdate(@PathVariable int id,
                                    Model model) {
        Customer customer = adminService.getCustomerById(id);
        model.addAttribute("admin", customer);
        return "updateForm"; //show the view with the  data to be updated
    }
    @RequestMapping(value = "updateForm/{id}", method = RequestMethod.POST)
    public String showFormForUpdate(@PathVariable int id , @ModelAttribute("admin") Customer admin) {
        adminService.updateAdmin(id, admin);
        return "redirect:/shopping/admin/showAllAdmins";
    }
}
