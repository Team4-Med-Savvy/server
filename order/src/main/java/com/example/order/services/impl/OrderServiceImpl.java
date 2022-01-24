package com.example.order.services.impl;

import com.example.order.entity.Orders;
import com.example.order.services.OrdersService;
import com.example.order.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrdersService {

    @Autowired
    OrdersRepository orderRepository;


    @Override
    public Orders select(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public void save(Orders order) {
        orderRepository.save(order);

    }

}
