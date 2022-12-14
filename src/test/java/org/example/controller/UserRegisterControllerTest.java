package org.example.controller;

import org.example.model.VerificationToken;
import org.example.repository.UserRegisterRepository;
import org.example.repository.VerificationTokenRepository;
import org.example.service.GmailSendEmail;
import org.example.service.UserRegisterServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterControllerTest {
    @InjectMocks
    private UserRegisterController userRegisterController;
    private UserRegisterServiceImpl userRegisterServiceImplMock;
    private VerificationTokenRepository verificationTokenRepositoryMock;
    private GmailSendEmail gmailSendEmailMock;
    private  MockMvc mockMvc;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void registration() {

    }

    @Test
    void addUser() {
    }

    @Test
    void verifyUserAccount() {
    }
}