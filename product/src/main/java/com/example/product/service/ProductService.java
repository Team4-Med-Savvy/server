package com.example.product.service;

import com.example.product.entity.Product;

public interface ProductService {
    Product select(String id);
    void save(Product product);
    void  delete(String id);
    Iterable<Product> findProduct(String id);
    Product findByTitle(String title);

}
