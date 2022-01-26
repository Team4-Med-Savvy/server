package com.example.userauth.services.feignServices;

import com.example.userauth.dto.MerchantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "merchant")
public interface MerchantFeignService {

    @PostMapping("/merchant")
    void save(MerchantDto merchant);
}
