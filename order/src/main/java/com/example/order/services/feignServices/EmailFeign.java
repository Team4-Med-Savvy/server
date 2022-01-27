package com.example.order.services.feignServices;

import com.example.order.dto.EmailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "email")
public interface EmailFeign {
    @PostMapping("/email")
    void sendMail(EmailDto emailDto);
}
