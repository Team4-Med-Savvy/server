package com.example.userauth.services.impl;

import com.example.userauth.entity.User;
import com.example.userauth.repositories.UserRepository;
import com.example.userauth.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    UserRepository userRepository;

    @Override
    public User select(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }



}
