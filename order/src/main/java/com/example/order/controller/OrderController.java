package com.example.order.controller;

import com.example.order.Request.RequestDto;
import com.example.order.Response.ResponseMerchantDto;
import com.example.order.dto.EmailDto;
import com.example.order.dto.OrderedProductsDto;
import com.example.order.dto.OrdersDto;
import com.example.order.dto.ProductDetailDto;
import com.example.order.entity.OrderedProducts;
import com.example.order.entity.Orders;
import com.example.order.services.OrderedProductsService;
import com.example.order.services.OrdersService;
import com.example.order.services.feignServices.EmailFeign;
import com.example.order.services.feignServices.MerchantFeign;
import com.example.order.services.feignServices.ProductFeign;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrdersService orderService;

    @Autowired
    private EmailFeign emailFeign;

    @Autowired
    private ProductFeign productFeign;

    @Autowired
    private OrderedProductsService orderedProductsService;

    @Autowired
    private MerchantFeign merchantFeign;

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
                    throw new Exception(product.getProductId()+" Out of stock");
                }else{
                    prods+=productDto.getName()+", ";
                }
            }
            Orders order = new Orders();
            BeanUtils.copyProperties(orderDto,order);
            orderService.save(order);

            OrderedProducts orderedProducts=new OrderedProducts();
            BeanUtils.copyProperties(orderDto,orderedProducts);
            orderedProducts.setOrders(order);
            orderedProductsService.save(orderedProducts);

            for(OrderedProductsDto product : orderDto.getProducts()){
                ResponseMerchantDto productDto=merchantFeign.findProductPrice(product.getProductId(),product.getMerchantId());
                productDto.setStock(productDto.getStock()-product.getQuantity());
                merchantFeign.save(productDto);
            }
            emailFeign.sendMail(new EmailDto("","Order Succesfully Placed!!","You have ordered "+ prods +"from Med-Savvy and it will be delivered to you shortly \n Thank you",orderDto.getUserId()));

        }catch(Exception e){
            System.out.println(e);
        }
    }

}
