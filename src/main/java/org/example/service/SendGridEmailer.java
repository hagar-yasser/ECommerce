package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
public class SendGridEmailer {

    public void sedEmail(String toEmail , String body , String subject){



    }

}
//    final String fromEmail = "anasroshdiii@gmail.com"; //requires valid gmail id
//    final String password = "hyoyujqzeamgkjhm"; // correct password for gmail id
//    final String toEmail = "anasroshdiii@gmail.com";
//
//    Properties props = new Properties();
//            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
//                    props.put("mail.smtp.port", "587"); //TLS Port
//                    props.put("mail.smtp.auth", "true"); //enable authentication
//                    props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
//
//                    //create Authenticator object to pass in Session.getInstance argument
//                    Authenticator auth = new Authenticator() {
////override the getPasswordAuthentication method
//protected PasswordAuthentication getPasswordAuthentication() {
//        return new PasswordAuthentication(fromEmail, password);
//        }
//        };
//        Session session = Session.getInstance(props, auth);
