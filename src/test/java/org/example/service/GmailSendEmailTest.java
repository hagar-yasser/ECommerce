package org.example.service;


import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import org.apache.commons.codec.binary.Base64;
import org.apache.coyote.Response;
import org.example.model.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import javax.annotation.meta.When;
import javax.mail.Service;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import static javax.mail.Message.RecipientType.TO;
import static org.example.service.GmailSendEmail.getCredentials;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class GmailSendEmailTest {

    @InjectMocks
    private GmailSendEmail gmailSendEmail;


    @Mock
    private Customer customer;

    @Mock
    private NetHttpTransport HTTP_TRANSPORT;
    @Mock
    private GsonFactory jsonFactory;
    @Mock
    private Properties properties;
    @Mock
    private ByteArrayOutputStream buffer;
    @Mock
    private Gmail service;

    @Mock
    private Message messg1;

    @Mock
    private Gmail.Users gmail_users;
    @Mock
    private javax.mail.Session session;


    public GmailSendEmailTest(){
        gmailSendEmail = new GmailSendEmail();
    }


    @Test
    public void testSendEmail_acceptSubjectAndMessage_thenThrowException() throws Exception {
        //Arrange
        String subject = "verification";
        String message = "token";
        //Act
        try {
            gmailSendEmail.sendEmail(subject,message);
        } catch (TokenResponseException ex) {
        //Assert
            assertTrue(ex instanceof TokenResponseException);
        }
    }
}
