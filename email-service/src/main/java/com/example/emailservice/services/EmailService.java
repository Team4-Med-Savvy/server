package com.example.emailservice.services;

import com.example.emailservice.dto.EmailDto;

public interface EmailService {
    void sendSimpleMessage(String email,String subject,String message );
}
