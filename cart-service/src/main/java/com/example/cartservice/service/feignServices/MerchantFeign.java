package com.example.cartservice.service.feignServices;

import com.example.cartservice.dto.MerchantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "merchant")
public interface MerchantFeign {
    @GetMapping("/merchant/{id}")
    MerchantDto select(@PathVariable(value = "id") String id);

}
