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
    public void sendSimpleMessage(EmailDto emailDto) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noreply@med-savvy.com");
            message.setTo(emailDto.getToAddress());
            message.setSubject(emailDto.getSubject());
            message.setText(emailDto.getMessage());
            emailSender.send(message);
    }



}