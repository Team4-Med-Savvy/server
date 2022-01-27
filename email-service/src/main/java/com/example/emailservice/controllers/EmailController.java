package com.example.emailservice.controllers;

import com.example.emailservice.dto.EmailDto;
import com.example.emailservice.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping()
    void sendMail(@RequestBody EmailDto emailDto){
        emailService.sendSimpleMessage(emailDto);
    }
}
