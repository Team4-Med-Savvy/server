package com.example.order.services;

import com.example.order.dto.OrderedProductsDto;
import com.example.order.entity.OrderedProducts;
import com.example.order.entity.Orders;

import java.util.List;

public interface OrderedProductsService {
    OrderedProducts select(Long id);
    void save(OrderedProducts orderedProducts);
    List<OrderedProducts> findByOrdersObject(Orders order);

}
