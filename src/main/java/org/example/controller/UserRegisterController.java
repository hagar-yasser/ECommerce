package org.example.controller;

import org.example.model.Customer;
import org.example.model.VerificationToken;
import org.example.repository.AdminRepositoryImpl;
import org.example.repository.VerificationTokenRepository;
import org.example.service.AdminServiceImpl;
import org.example.service.EmailSenderService;
import org.example.service.UserRegisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class UserRegisterController {

    private UserRegisterServiceImpl userRegisterService;
    private VerificationTokenRepository verificationTokenRepository;
    private AdminRepositoryImpl adminRepository;//later will be used to get the email of admin that
    private EmailSenderService emailSenderService;

    @Autowired
    public UserRegisterController(UserRegisterServiceImpl userRegisterServiceImpl,
                                  VerificationTokenRepository verificationTokenRepository,
                                  AdminRepositoryImpl adminRepository,EmailSenderService emailSenderService) {
        this.userRegisterService = userRegisterServiceImpl;
        this.verificationTokenRepository = verificationTokenRepository;
        this.adminRepository = adminRepository;
        this.emailSenderService = emailSenderService;
    }

    @RequestMapping("/register")
    public String registration(Model model) {
        model.addAttribute("user", new Customer());
        return "register";
    }

    @RequestMapping("/register")
    public String addUser(@Valid @ModelAttribute("customer") Customer customer,
                          BindingResult bindingResult,Model model) {

        //check if there is user before used this email
        Customer existingUser = userRegisterService.findByEmail(customer.getEmail());
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if(existingUser!=null) {
            model.addAttribute("error","There is already an account with this email:" + customer.getEmail());
            return "/registration";
        }else {
            userRegisterService.registerUser(customer);
            VerificationToken verificationToken = new VerificationToken(customer);
            verificationTokenRepository.save(verificationToken);


            //with properties
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(customer.getEmail());
            mailMessage.setSubject("Complete Registration process please!!!");
            mailMessage.setFrom("anasroshdiii@gmail.com");//later we can take it from admin findByEmail
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8082/verifyAccount?token="+verificationToken.getToken());
            emailSenderService.sendEmail(mailMessage);



            model.addAttribute("email", customer.getEmail());
            return "redirect:/registrationSuccess";
        }

    }

    @RequestMapping("/verifyAccount")
    public String verifyUserAccount(Model model, @RequestParam("token") String verificationToken){
        VerificationToken token = verificationTokenRepository.findByVerificationToken(verificationToken);

        if(token != null)
        {
            Customer customer = userRegisterService.findByEmail(token.getUser().getEmail());
            customer.setIsActivated(true);
            userRegisterService.registerUser(customer);
        }
        else
        {
            model.addAttribute("message","The link is invalid or broken!");
            return "errorInVerification";
        }
        return "verifyAccount";
    }


}