package com.example.order.controller;

import com.example.order.Request.RequestDto;
import com.example.order.dto.EmailDto;
import com.example.order.dto.OrderedProductsDto;
import com.example.order.dto.OrdersDto;
import com.example.order.dto.ProductDetailDto;
import com.example.order.entity.Orders;
import com.example.order.services.OrdersService;
import com.example.order.services.feignServices.EmailFeign;
import com.example.order.services.feignServices.ProductFeign;
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

    @Autowired
    EmailFeign emailFeign;

    @Autowired
    ProductFeign productFeign;

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
    void save(@RequestBody RequestDto orderDto){
        try{
            String prods="";
            for(OrderedProductsDto product : orderDto.getProducts()){
                ProductDetailDto productDto=productFeign.finddetail(product.getProductId(),product.getMerchantId());
                if (productDto.getMerchantStock()<product.getQuantity()){
                    throw new Exception("Out of stock");
                }else{
                    prods+=productDto.getName()+", ";
                }
            }
            Orders order = new Orders();
            BeanUtils.copyProperties(orderDto,order);
            orderService.save(order);

            emailFeign.sendMail(new EmailDto("","Order Succesfully Placed!!","You have ordered "+ prods +"from Med-Savvy and it will be delivered to you shortly \n Thank you",orderDto.getUserId()));

        }catch(Exception e){
            System.out.println(e);
        }
    }

}
