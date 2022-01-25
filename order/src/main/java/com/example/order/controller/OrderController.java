package com.example.order.controller;

import com.example.order.dto.OrdersDto;
import com.example.order.entity.Orders;
import com.example.order.services.OrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "order")
public class OrderController {

    @Autowired
    OrdersService orderService;

    @GetMapping(value = "/{id}")
    OrdersDto select(@PathVariable("id") Long id){
        Orders order = orderService.select(id);
        OrdersDto orderDto = new OrdersDto();
        BeanUtils.copyProperties(order,orderDto);
        return orderDto;

    }

    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT})
    void save(@RequestBody OrdersDto orderDto){
        Orders order = new Orders();
        BeanUtils.copyProperties(orderDto,order);
        orderService.save(order);
    }
    


}
