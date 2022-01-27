package com.example.merchant.controller;

import com.example.merchant.dto.ProductDto;
import com.example.merchant.dto.ResponseMerchantDto;
import com.example.merchant.entity.Merchant;
import com.example.merchant.entity.Product;
import com.example.merchant.service.MerchantService;
import com.example.merchant.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/merchant/prod")
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


    List<ProductDto> findall()
    {
        List<ProductDto> productDtos=new ArrayList<>();
        Iterable<Product> products=productService.findall();

        for(Product prod:products){
        productDtos.add(createDTOFromEntity(prod));
        }

        return productDtos;
    }

    @GetMapping(value = "/{pid}/{mid}")
    ResponseMerchantDto findProductPrice(@PathVariable(value = "pid") String pid,@PathVariable(value = "mid") String mid)
    {
        List<ProductDto> productDtos=findall();
        ResponseMerchantDto responseMerchantDto=new ResponseMerchantDto();

        for(ProductDto dto:productDtos)
        {
            String productid=dto.getProductId();
            String merchantid=dto.getMerchantId();

            if(productid.equals(pid) && merchantid.equals(mid))
            {
                responseMerchantDto.setMerchantId(merchantid);
                responseMerchantDto.setProdcutId(productid);
                responseMerchantDto.setPrice(dto.getPrice());
                responseMerchantDto.setStock(dto.getStock());
            }

        }

        return responseMerchantDto;
    }
    public Product createEntityFromDto(ProductDto dto){
        Product product=new Product();
        BeanUtils.copyProperties(dto,product);
        product.setMerchant(merchantService.select(dto.getMerchantId()));
        return product;
    }

    public ProductDto createDTOFromEntity(Product entity){
        ProductDto productDto=new ProductDto();
        BeanUtils.copyProperties(entity,productDto);
        productDto.setMerchantId(entity.getMerchant().getId());
        return productDto;
    }
}
