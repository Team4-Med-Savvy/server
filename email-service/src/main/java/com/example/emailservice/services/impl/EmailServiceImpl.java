package com.example.emailservice.services.impl;

import com.example.emailservice.dto.EmailDto;
import com.example.emailservice.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendSimpleMessage(String email,String subject,String message) {
            SimpleMailMessage messageBox = new SimpleMailMessage();
            messageBox.setFrom("noreply@med-savvy.com");
            messageBox.setTo(email);
            messageBox.setSubject(subject);
            messageBox.setText(message);
            emailSender.send(messageBox);
    }



}