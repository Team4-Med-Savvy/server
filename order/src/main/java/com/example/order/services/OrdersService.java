package com.example.order.services;

import com.example.order.entity.OrderedProducts;
import com.example.order.entity.Orders;

import java.util.List;

public interface OrdersService {
    List<Orders> findByUserId(String id);

    Orders select(Long id);
    void save(Orders order);
}
