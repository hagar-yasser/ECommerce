package org.example.service;

import org.example.model.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GmailSendEmailTest {
    GmailSendEmail gmailSendEmail = new GmailSendEmail();
    Customer customer = new Customer();
    @Test
    void testSendGmail() throws Exception {
        gmailSendEmail.sendEmail("ddffd","fdfdfddf",customer);
    }

}