package com.example.cartservice.service;

import com.example.cartservice.entity.Cart;

public interface CartService {
    Cart findById(String id);
    Cart findByEmail(String email);
    void save(Cart cart);
}
