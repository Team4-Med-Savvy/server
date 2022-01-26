package com.example.order.services;

import com.example.order.dto.OrderedProductsDto;
import com.example.order.entity.OrderedProducts;

import java.util.List;

public interface OrderedProductsService {
    OrderedProducts select(Long id);
    void save(OrderedProducts orderedProducts);
}
