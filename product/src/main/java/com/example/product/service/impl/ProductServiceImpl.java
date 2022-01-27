package com.example.product.service.impl;

import com.example.product.entity.Product;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    ProductRepository productRepository;

    @Override
    public Product findByTitle(String title) {
        return productRepository.findByTitle(title);
    }
    @Override
    public Product select(String id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(String id) {
         productRepository.deleteById(id);
    }

    @Override
    public Iterable<Product> findProduct(String id) {
        return productRepository.findBycategory(id);
    }
}
