package com.project.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {
    
    @Autowired
    private EmailService emailService;
    @Test
    void testSendMail(){
        emailService.sendEmail("yp0231@srmist.edu.in", "Testing java mail sender", "Hi, Sanskriti kaisi ho aap??");
    }
}
