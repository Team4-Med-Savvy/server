package com.example.cartservice.service.feignServices;

import com.example.cartservice.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="product")
public interface ProductFeign {
    ProductDto select(String id);
}
