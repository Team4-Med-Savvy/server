package com.example.merchant.service.impl;

import com.example.merchant.entity.Product;
import com.example.merchant.repository.ProductRepository;
import com.example.merchant.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product select(Long id) {
        return null;
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void delete(Long id) {

    }
}
