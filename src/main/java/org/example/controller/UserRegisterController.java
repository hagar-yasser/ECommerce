package org.example.controller;

import org.example.model.Customer;
import org.example.model.VerificationToken;
import org.example.repository.VerificationTokenRepository;
import org.example.service.GmailSendEmail;
import org.example.service.UserRegisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/registration/")

public class UserRegisterController {

    private UserRegisterServiceImpl userRegisterService;
    private VerificationTokenRepository verificationTokenRepository;
    private GmailSendEmail gmailSendEmail;

    @Autowired
    public UserRegisterController(UserRegisterServiceImpl userRegisterServiceImpl,
                                  VerificationTokenRepository verificationTokenRepository,
                                  GmailSendEmail gmailSendEmail) {
        this.userRegisterService = userRegisterServiceImpl;
        this.verificationTokenRepository = verificationTokenRepository;
        this.gmailSendEmail = gmailSendEmail;

    }

    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("customer", new Customer());
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@Valid @ModelAttribute("customer") Customer customer,
                          BindingResult bindingResult,Model model) throws Exception {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        //check if there is user before used this email
        Customer existingUser = userRegisterService.findByEmail(customer.getEmail());
        if(existingUser!=null) {
            model.addAttribute("error","There is already an account with this email:" + customer.getEmail());
            return "alreadyRegistered";
        }else {

            //register our user to DB
            userRegisterService.registerUser(customer);
            VerificationToken verificationToken = new VerificationToken(customer);
            verificationTokenRepository.save(verificationToken);

            //sending message with Gmail API
            String subject = "This verification mail to complete registration process";
            String message = "Please Click on the link "+"http://localhost:8080/ECommerce/shopping/registration/verifyAccount?token="
                    +verificationToken.getToken();
            gmailSendEmail.sendEmail(subject,message);

            return "verification";
        }

    }

    @GetMapping("/verifyAccount")
    public String verifyUserAccount(Model model, @RequestParam("token") String verificationToken){
        //get verification token for customer to activate his status
        VerificationToken token = verificationTokenRepository.findByVerificationToken(verificationToken);

        if(token != null)
        {
            Customer customer = userRegisterService.findByEmail(token.getUser().getEmail());
            customer.setIsActivated(true);
        }
        else
        {
            model.addAttribute("message","The link is invalid or broken!");
            return "errorInVerification";
        }
        return "login";
    }


}