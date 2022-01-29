package com.example.order.controller;

import com.example.order.Response.OrdersResponse;
import com.example.order.dto.OrderedProductsDto;
import com.example.order.dto.ProductDetailDto;
import com.example.order.entity.OrderedProducts;
import com.example.order.entity.Orders;
import com.example.order.services.OrderedProductsService;
import com.example.order.services.OrdersService;
import com.example.order.services.feignServices.ProductFeign;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/orderedproducts")
public class OrderProductsController {

    @Autowired
    OrdersService ordersService;
    @Autowired
    OrderedProductsService orderedProductsService;
    @Autowired
    ProductFeign productFeign;

    @GetMapping(value = "/{id}")
    List<OrdersResponse> findAnOrder(@PathVariable("id") Long id){
        Orders order = ordersService.select(id);
        List<OrderedProducts> orderedProducts=orderedProductsService.findByOrdersObject(order);
        List<OrdersResponse> result=new ArrayList<>();
        for (OrderedProducts product: orderedProducts){
            ProductDetailDto productDetail=productFeign.finddetail(product.getProductId(),product.getMerchantId());
            OrdersResponse ordersResponse=new OrdersResponse();
            ordersResponse.setMerchantId(product.getMerchantId());
            ordersResponse.setProductId(product.getProductId());
            ordersResponse.setAmount(product.getAmount());
            ordersResponse.setName(productDetail.getName());
            ordersResponse.setImageUrl(productDetail.getImageUrl());
            ordersResponse.setQuantity(product.getQuantity());
            result.add(ordersResponse);
        }
        return result;
    }


    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT})
    void save(@RequestBody OrderedProducts orderedProductsDto){
        orderedProductsService.save(orderedProductsDto);
    }

}
