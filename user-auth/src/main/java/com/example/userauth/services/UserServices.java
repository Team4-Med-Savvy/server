package com.example.userauth.services;

import com.example.userauth.entity.User;

public interface UserServices {
    User select(String id);
    void save(User user);
}
