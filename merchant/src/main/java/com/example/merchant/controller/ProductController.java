package com.example.merchant.controller;

import com.example.merchant.dto.ProductDto;
import com.example.merchant.entity.Merchant;
import com.example.merchant.entity.Product;
import com.example.merchant.service.MerchantService;
import com.example.merchant.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    MerchantService merchantService;

    @GetMapping(value="/{idFromDT}")
    ProductDto select(@PathVariable(value = "idFromDT") Long id)
    {
        Product product=productService.select(id);
        ProductDto productDto=createDTOFromEntity(product);
        return productDto;
    }

    @RequestMapping(method ={RequestMethod.POST,RequestMethod.PUT})
    void save(@RequestBody ProductDto productDto)
    {
        Product product=createEntityFromDto(productDto);
        productService.save(product);
    }

    @DeleteMapping(value = "/{idFromProduct}")
    void delete(@PathVariable(value = "idFromProduct") Long id)
    {
        productService.delete(id);
    }


    public Product createEntityFromDto(ProductDto dto){
        Product product=new Product();
        BeanUtils.copyProperties(dto,product);

        return product;
    }

    public ProductDto createDTOFromEntity(Product entity){

        ProductDto productDto=new ProductDto();
        BeanUtils.copyProperties(entity,productDto);
        return productDto;
    }
}
