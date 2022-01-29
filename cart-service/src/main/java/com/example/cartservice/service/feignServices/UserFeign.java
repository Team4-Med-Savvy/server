package com.example.cartservice.service.feignServices;

import com.example.cartservice.Request.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user")
public interface UserFeign {

    @PostMapping("/user/getuser")
    UserDto getUser(@RequestBody String id);
}
