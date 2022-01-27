package com.example.emailservice.controllers;

import com.example.emailservice.dto.EmailDto;
import com.example.emailservice.dto.UserDto;
import com.example.emailservice.services.EmailService;
import com.example.emailservice.services.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @Autowired
    UserFeign userFeign;

    @PostMapping()
    void sendMail(@RequestBody EmailDto emailDto){
        UserDto userDto=userFeign.getUser(emailDto.getUserId());
        emailService.sendSimpleMessage(userDto.getEmail(),emailDto.getSubject(),emailDto.getMessage());
    }
}
