package com.example.cartservice.repository;

import com.example.cartservice.dto.CartDto;
import com.example.cartservice.entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart,String> {
    Cart findByEmail(String email);
}
