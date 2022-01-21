package com.example.userauth.controllers;

import com.example.userauth.dto.UserDto;
import com.example.userauth.entity.User;
import com.example.userauth.services.UserServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServices userServices;
//    @Autowired
//    private PasswordEncoder passwordEncoder;


    @PostMapping
    void save(@RequestBody UserDto userDto){
        userServices.save(copyFromDTO(userDto));
    }

    private User copyFromDTO(UserDto userDto){
        User user = new User();
//        user.setName(userDto.getName());
//        user.setMerchant(userDto.getMerchant());
//        user.setUsername(userDto.getEmail());
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        user.setPoints(userDto.getPoints());
//        user.setEmail(userDto.getEmail());
        BeanUtils.copyProperties(userDto,user);
        return user;
    }

}
