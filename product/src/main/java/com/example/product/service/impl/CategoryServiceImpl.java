package com.example.product.service.impl;

import com.example.product.entity.Category;
import com.example.product.repository.CategoryRepository;
import com.example.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);

    }

    @Override
    public void delete(String id) {
        categoryRepository.deleteById(id);
    }
}
