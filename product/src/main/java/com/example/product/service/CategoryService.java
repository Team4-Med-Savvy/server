package com.example.product.service;

import com.example.product.entity.Category;

public interface CategoryService {
    Category select(String id);
    void save(Category category);
    void  delete(String id);

}
