package org.example.controller;

import org.example.model.Customer;
import org.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @RequestMapping(value = "login" ,method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login( @RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, ModelMap modelMap) {
        try{
        Customer customer = loginService.findByEmail(email);

            if (customer == null) {
                modelMap.put("error", "Invalid Account!!!");
                return "login";
            } else if (!customer.getIsActivated()) {
                modelMap.put("message", "Account Suspended.... Enter Email To Re Activate It");
                return "enterEmail";
            } else if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                loginService.setLoggedIn(customer.getCustomerId());
                session.setAttribute("customer", customer);
                //return "success";
                if(customer.getIsAdmin()){  // to let the admin manage other admins and items
                    return "redirect:/shopping/items/allForAdmin";
                }
                return "redirect:/shopping/items/all";
            } else if (customer.getEmail().equals(email) && !customer.getPassword().equals(password) && customer.getWrongPasswordTrials() < 3) {
                loginService.incrementWrongPassTrials(customer.getCustomerId());
                modelMap.put("error", "Wrong Password....Try Again!!");
                return "login";
            } else {
                loginService.deActivateUser(customer.getCustomerId());
                modelMap.put("message", "Account Suspended.... Enter Email To Re Activate It");
                return "enterEmail";
            }
        } catch (Exception e) {
            modelMap.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession session, ModelMap modelMap) {
        try {
            Customer customer = (Customer) session.getAttribute("customer");
            loginService.setLoggedOut(customer.getCustomerId());
            session.invalidate();
            return "redirect:/shopping/login/login";
        }catch (Exception e) {
            modelMap.addAttribute("message", e.getMessage());
            return "error";
        }
    }

}
