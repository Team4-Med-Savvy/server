package com.example.product.controller;

import com.example.product.dto.CategoryDto;
import com.example.product.entity.Category;
import com.example.product.service.impl.CategoryServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryServiceImpl categoryServiceimpl;

    @GetMapping(value="/{id}")
    public Category select(@PathVariable(value = "id") String id){
        return categoryServiceimpl.select(id);
    }

    @RequestMapping(method ={RequestMethod.POST,RequestMethod.PUT})
    void save(@RequestBody CategoryDto categoryDto){
        Category category=createEntityFromDto(categoryDto);
        categoryServiceimpl.save(category);

    }

    @DeleteMapping(value = "/{id}")
    void  delete(@PathVariable(value = "id") String id){
        categoryServiceimpl.delete(id);

    }

    Category createEntityFromDto(CategoryDto categoryDto){
        Category category=new Category();
        BeanUtils.copyProperties(categoryDto,category);
        return category;
    }

    CategoryDto createDtoFromEntity(Category category)
    {
        CategoryDto categoryDto=new CategoryDto();
        BeanUtils.copyProperties(categoryDto,category);
        return categoryDto;
    }

}
