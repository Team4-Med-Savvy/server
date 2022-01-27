package com.example.emailservice.services;

import com.example.emailservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user")
public interface UserFeign {
    @GetMapping("/getuser/{id}")
    public UserDto getUser(@PathVariable(name = "id") String id);
}
