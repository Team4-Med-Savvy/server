package com.example.emailservice.services;

import com.example.emailservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user")
public interface UserFeign {

    @PostMapping("/user/getuser")
    UserDto getUser(@RequestBody String id);
}
