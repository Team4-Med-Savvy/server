package com.example.cartservice.service.impl;

import com.example.cartservice.entity.Cart;
import com.example.cartservice.repository.CartRepository;
import com.example.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Override
    public Cart findById(String id) {
        return cartRepository.findById(id).get();
    }

    @Override
    public Cart findByEmail(String email) {
        return cartRepository.findByEmail(email);
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void deleteByEmail(String id) {
        cartRepository.deleteByEmail(id);
    }
}
