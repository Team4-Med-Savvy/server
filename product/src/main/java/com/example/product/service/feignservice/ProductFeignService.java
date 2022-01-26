package com.example.product.service.feignservice;

import com.example.product.dto.MerchantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="merchant")
public interface ProductFeignService {
    @GetMapping("/merchant/{id}")
    MerchantDto findById(@PathVariable String id);
}
