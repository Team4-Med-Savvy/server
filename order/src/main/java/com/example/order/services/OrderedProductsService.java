package com.example.order.services;

import com.example.order.entity.OrderedProducts;

public interface OrderedProductsService {

    OrderedProducts select(Long id);
    void save(OrderedProducts orderedProducts);
}
