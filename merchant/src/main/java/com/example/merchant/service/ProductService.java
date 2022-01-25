package com.example.merchant.service;

import com.example.merchant.entity.Product;

public interface ProductService {
    Product select(Long id);
    void save(Product product);
    void delete(Long id);

}
