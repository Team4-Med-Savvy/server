package com.example.userauth.services;

import com.example.userauth.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServices extends UserDetailsService {
    User findByUsername(String username);
    User select(String id);
    void save(User user);
}
