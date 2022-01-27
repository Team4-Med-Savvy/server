package com.example.emailservice.services;

import com.example.emailservice.dto.EmailDto;

public interface EmailService {
    void sendSimpleMessage(EmailDto emailDto);
}
