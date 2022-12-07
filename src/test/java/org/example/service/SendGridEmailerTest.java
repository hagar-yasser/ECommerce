package org.example.service;

import org.junit.jupiter.api.Test;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class SendGridEmailerTest {


    //tls auth



    //ssl auth
//            props.put("mail.imap.host", "imap.gmail.com"); //SMTP Host
//            props.put("mail.imap.socketFactory.port", "993"); //SSL Port
//            props.put("mail.imap.socketFactory.class",
//                    "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
//            props.put("mail.imap.auth", "true"); //Enabling SMTP Authentication
//            props.put("mail.imap.port", "465"); //SMTP Port

    //create Authenticator object to pass in Session.getInstance argument

    @Test
    void testSendMail(){


        final String fromEmail = "anasroshdiii@gmail.com"; //requires valid gmail id
        final String password = "AmasrhyaomyM"; // correct password for gmail id
        final String toEmail = "myemail@yahoo.com"; // can be any email id

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.setProperty("mail.transport.protocol", "smtp");

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        SendGridEmailer.sendEmail(session,"anasroshdiii@gmail.com","ddfdfdf","fddffd");
}
}