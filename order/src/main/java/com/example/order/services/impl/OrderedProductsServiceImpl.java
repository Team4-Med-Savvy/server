package com.example.order.services.impl;

import com.example.order.dto.OrderedProductsDto;
import com.example.order.entity.OrderedProducts;
import com.example.order.repository.OrderedProductsRepository;
import com.example.order.services.OrderedProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedProductsServiceImpl implements OrderedProductsService {

    @Autowired
    OrderedProductsRepository orderedProductsRepository;

    @Override
    public OrderedProducts select(Long id) {
        return orderedProductsRepository.findById(id).get();
    }



    @Override
    public void save(OrderedProducts orderedProducts) {

        orderedProductsRepository.save(orderedProducts);
    }

}
