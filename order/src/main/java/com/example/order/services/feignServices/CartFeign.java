package com.example.order.services.feignServices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cart")
public interface CartFeign {
    @PostMapping("/cart/clear")
    void clear(@RequestBody String id);
}
