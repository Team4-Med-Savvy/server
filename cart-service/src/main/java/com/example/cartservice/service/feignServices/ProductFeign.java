package com.example.cartservice.service.feignServices;

import com.example.cartservice.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="product")
public interface ProductFeign {
    @GetMapping("/product/{id}")
    ProductDto select(@PathVariable(name = "id") String id);
}
