package com.example.merchant.controller;

import com.example.merchant.Response.RequestDto;
import com.example.merchant.dto.ProductDto;
import com.example.merchant.dto.ResponseMerchantDto;
import com.example.merchant.entity.Merchant;
import com.example.merchant.entity.Product;
import com.example.merchant.service.MerchantService;
import com.example.merchant.service.ProductService;
import com.example.merchant.service.feignServices.ProductFeign;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value="/merchant/prod")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    MerchantService merchantService;

    @Autowired
    ProductFeign productFeign;

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
    ResponseMerchantDto findProductPrice(@PathVariable(value = "pid") String pid, @PathVariable(value = "mid") String mid)
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
                responseMerchantDto.setProductId(productid);
                responseMerchantDto.setPrice(dto.getPrice());
                responseMerchantDto.setStock(dto.getStock());
                break;
            }
        }
        return responseMerchantDto;
    }

    @RequestMapping(value="/{pid}/{mid}",method ={RequestMethod.POST,RequestMethod.PUT})
    void changeProductPrice(@PathVariable(value = "pid") String pid,@PathVariable(value = "mid") String mid, @RequestBody RequestDto requestDto)
    {

        List<ProductDto> productDtos=findall();
        ResponseMerchantDto responseMerchantDto=new ResponseMerchantDto();
        Product product=new Product();

        for(ProductDto dto:productDtos)
        {
            String productid=dto.getProductId();
            String merchantid=dto.getMerchantId();

            if(productid.equals(pid) && merchantid.equals(mid))
            {
                responseMerchantDto.setId(dto.getId());
                responseMerchantDto.setMerchantId(merchantid);
                responseMerchantDto.setProductId(productid);
                responseMerchantDto.setPrice(dto.getPrice());
                responseMerchantDto.setStock(dto.getStock());
                break;
            }
        }

        if(responseMerchantDto.getId()!=null){
            product.setId(responseMerchantDto.getId());
        }
        product.setProductId(pid);
        product.setPrice(requestDto.getPrice());
        product.setStock(requestDto.getStock());
        product.setMerchant(merchantService.select(mid));
        productFeign.editMerchantList(pid,mid);
        productService.save(product);
    }

    public Product createEntityFromDto(ProductDto dto){
        Product product=new Product();
        BeanUtils.copyProperties(dto,product);
        System.out.println(dto.getMerchantId());
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
