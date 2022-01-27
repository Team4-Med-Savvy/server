package com.example.order.controller;

import com.example.order.dto.OrderedProductsDto;
import com.example.order.entity.OrderedProducts;
import com.example.order.services.OrderedProductsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "orderedproducts")
public class OrderProductsController {

    @Autowired
    OrderedProductsService orderedProductsService;

    @GetMapping(value = "/{id}")
    OrderedProductsDto findAnOrder(@PathVariable("id") Long id){
        OrderedProducts orderedProducts=orderedProductsService.select(id);
        OrderedProductsDto orderedProductsDto=new OrderedProductsDto();
        BeanUtils.copyProperties(orderedProducts,orderedProductsDto);
        return orderedProductsDto;
    }


    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT})
    void save(@RequestBody OrderedProductsDto orderedProductsDto){
        OrderedProducts orderedProducts = new OrderedProducts();
        BeanUtils.copyProperties(orderedProductsDto,orderedProducts);
        orderedProductsService.save(orderedProducts);
    }

}
