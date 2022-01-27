package com.example.merchant.service.feignServices;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "product")
public interface ProductFeign {

}
