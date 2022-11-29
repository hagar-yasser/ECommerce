package org.example.controller;


import org.example.entity.Customer;
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
        model.addAttribute("customer", new Customer());
        return "addAdmin"; //the view that has the form to add admin
    }

    @RequestMapping(value = "addAdmin", method = RequestMethod.POST)
    public String addAdmin(@ModelAttribute("admin") Customer admin) {
        adminService.addAdmin(admin);
        return "redirect:/shopping/admin/showAllAdmins";
    }

    @RequestMapping(value="deleteAdmin/{id}",method = RequestMethod.GET)
    public String deleteAdmin(@PathVariable int id){
        adminService.deleteAdminById(id);
        return "redirect:/shopping/admin/showAllAdmins";
    }

    //not completed
    @RequestMapping("/updateAdmin/{id}")
    public String updateAdmin(@PathVariable("id") int id, Model model){
//        model.addAttribute("person", this.personService.getPersonById(id));
//        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }
}
