package com.example.order.controller;

import com.example.order.dto.OrdersDto;
import com.example.order.entity.Orders;
import com.example.order.services.OrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "order")
public class OrderController {

    @Autowired
    OrdersService orderService;


    @GetMapping(value = "/user/{id}")
    List<OrdersDto> findUserOrders(@PathVariable("id") String id){
        List<Orders> list= orderService.findByUserId(id);
        List<OrdersDto> result = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            OrdersDto orderDto = new OrdersDto();
            BeanUtils.copyProperties(list.get(i),orderDto);
            result.add(orderDto);
        }
        return result;
    }


    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT})
    void save(@RequestBody OrdersDto orderDto){
        Orders order = new Orders();
        BeanUtils.copyProperties(orderDto,order);
        orderService.save(order);
    }
    


}
