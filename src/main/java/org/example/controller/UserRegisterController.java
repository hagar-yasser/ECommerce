package org.example.controller;

import org.example.model.Customer;
import org.example.service.UserRegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class UserRegisterController {

    private UserRegisterService userRegisterService;

    public UserRegisterController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @RequestMapping("/register")
    public String registration(Model model) {
        model.addAttribute("user", new Customer());
        return "registration";
    }

    @RequestMapping("/register")
    public String addUser(@Valid @ModelAttribute("customer") Customer customer,
                          BindingResult bindingResult,Model model) {

        Customer customer1 = new Customer();
        String email = customer.getEmail();
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        customer1 = userRegisterService.findByEmail(email);
        if(customer1!=null) {
            model.addAttribute("error","There is already an account with this email:" + email);
            return "/registration";
        }
        return "redirect:/login";

    }
}