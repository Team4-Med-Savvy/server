package com.example.merchant.service;

import com.example.merchant.entity.Product;

import java.util.List;

public interface ProductService {
    Product select(Long id);
    void save(Product product);
    void delete(Long id);
    List<Product> findByMerchantId(String id);

}
