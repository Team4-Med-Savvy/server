package com.example.product.service.feignservice;

import com.example.product.dto.MerchantDto;
import com.example.product.dto.MerchantProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@FeignClient(name="merchant")
public interface ProductFeignService {
    @RequestMapping(value = "merchant/{id}", method = RequestMethod.GET)
    MerchantDto findById(@PathVariable("id") String id);

}
