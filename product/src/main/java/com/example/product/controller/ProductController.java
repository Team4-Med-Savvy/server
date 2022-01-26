package com.example.product.controller;


import com.example.product.dto.ProductDto;
import com.example.product.entity.Product;
import com.example.product.service.impl.ProductServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductServiceImpl productServiceImpl;


    @GetMapping(value="/{id}")
    public Product select(@PathVariable(value = "id") String id){
        return productServiceImpl.select(id);
    }

    @RequestMapping(method ={RequestMethod.POST,RequestMethod.PUT})
    void save(@RequestBody ProductDto productDto){
        Product category=createEntityFromDto(productDto);
        productServiceImpl.save(category);

    }

    @DeleteMapping(value = "/{id}")
    void  delete(@PathVariable(value = "id") String id){
        productServiceImpl.delete(id);

    }

    @GetMapping(value = "/findlist/{id}")
    List<ProductDto> findProduct(@PathVariable(value = "id") String id)
    {
        List<ProductDto> productDtos=new ArrayList<>();
        Iterable<Product> products=productServiceImpl.findProduct(id);

        for(Product temp:products)
        {
            productDtos.add(createDtoFromEntity(temp));
        }
        return productDtos;
    }
    Product createEntityFromDto(ProductDto productDto){
        Product product=new Product();
        BeanUtils.copyProperties(productDto,product);
        return product;
    }

    ProductDto createDtoFromEntity(Product product)
    {
        ProductDto productDto=new ProductDto();
        BeanUtils.copyProperties(product,productDto);
        return productDto;
    }

}
