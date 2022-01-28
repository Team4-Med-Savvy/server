package com.example.product.service;

import com.example.product.entity.Category;

import java.util.List;

public interface CategoryService {
    Category findByName(String id);
    void save(Category category);
    void  delete(String id);
    List<Category> findAll();

}
