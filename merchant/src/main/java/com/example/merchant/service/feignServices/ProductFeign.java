package com.example.merchant.service.feignServices;

import com.example.merchant.Response.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "product")
public interface ProductFeign {
    @PostMapping(value = "/product/edit/{pid}/{mid}")
    void editMerchantList(@PathVariable(name = "pid") String pid, @PathVariable(name = "mid") String mid);

    @GetMapping(value="/product/{id}")
    ProductDto select(@PathVariable(value = "id") String id);
}