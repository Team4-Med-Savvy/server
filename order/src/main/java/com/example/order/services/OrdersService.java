package com.example.order.services;

import com.example.order.entity.Orders;

public interface OrdersService {

    Orders select(Long id);
    void save(Orders order);
}
