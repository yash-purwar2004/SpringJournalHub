package com.project.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {
    
    // Used for sending mails
    @Autowired
    private JavaMailSender javaMailSender;

    // Method to send email
    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(body);

            // Send the email
            javaMailSender.send(mail);
            log.info("Email sent successfully to {}", to);
        } catch (Exception e) {
            // Log the error and provide meaningful message
            log.error("Exception while sending email to {}: {}", to, e.getMessage());
        }
    }
}
