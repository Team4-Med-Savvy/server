package com.example.order.services.feignServices;

import com.example.order.dto.ProductDetailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product")
public interface ProductFeign {

    @GetMapping("/product/productdetail/{pid}/{mid}")
    ProductDetailDto finddetail(@PathVariable(value = "pid") String pid, @PathVariable(value = "mid") String mid);
}
