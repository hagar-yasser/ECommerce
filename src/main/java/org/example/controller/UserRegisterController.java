package org.example.controller;

import org.example.model.Customer;
import org.example.model.VerificationToken;
import org.example.repository.VerificationTokenRepository;
//import org.example.service.EmailService;
import org.example.service.GmailSendEmail;
import org.example.service.SendGridEmailer;
import org.example.service.UserRegisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.validation.Valid;
import java.util.Properties;

@Controller
@RequestMapping("/registration/")
public class UserRegisterController {

    private UserRegisterServiceImpl userRegisterService;
    private VerificationTokenRepository verificationTokenRepository;
    private GmailSendEmail gmailSendEmail;
    private SendGridEmailer sendGridEmailer;

    @Autowired
    public UserRegisterController(UserRegisterServiceImpl userRegisterServiceImpl,
                                  VerificationTokenRepository verificationTokenRepository,
                                  GmailSendEmail gmailSendEmail,SendGridEmailer sendGridEmailer) {
        this.userRegisterService = userRegisterServiceImpl;
        this.verificationTokenRepository = verificationTokenRepository;
        this.gmailSendEmail = gmailSendEmail;
        this.sendGridEmailer = sendGridEmailer;

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
            String message = "Please Click on the link "+"http://localhost:8080/shopping/registration/verifyAccount?token="+verificationToken.getToken();
//            gmailSendEmail.sendEmail(subject,message);
            final String fromEmail = "anasroshdiii@gmail.com"; //requires valid gmail id
            final String password = "AmasrhyaomyM"; // correct password for gmail id
            final String toEmail = "myemail@yahoo.com"; // can be any email id

            //tls auth

            System.out.println("TLSEmail Start");
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            props.setProperty("mail.transport.protocol", "smtp");

            //ssl auth
//            props.put("mail.imap.host", "imap.gmail.com"); //SMTP Host
//            props.put("mail.imap.socketFactory.port", "993"); //SSL Port
//            props.put("mail.imap.socketFactory.class",
//                    "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
//            props.put("mail.imap.auth", "true"); //Enabling SMTP Authentication
//            props.put("mail.imap.port", "465"); //SMTP Port

            //create Authenticator object to pass in Session.getInstance argument
            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };
            Session session = Session.getInstance(props, auth);

            sendGridEmailer.sendEmail(session, toEmail,"TLSEmail Testing Subject", "TLSEmail Testing Body");

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
            userRegisterService.registerUser(customer);
        }
        else
        {
            model.addAttribute("message","The link is invalid or broken!");
            return "errorInVerification";
        }
        return "login";
    }


}