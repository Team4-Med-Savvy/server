package com.example.order.services.feignServices;

import com.example.order.dto.MerchantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "merchant")
public interface MerchantFeign {
    @GetMapping("/merchant/{id}")
    MerchantDto findById(@PathVariable String id);

}
