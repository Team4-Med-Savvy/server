package com.example.order.services.feignServices;

import com.example.order.Response.ResponseMerchantDto;
import com.example.order.dto.MerchantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "merchant")
public interface MerchantFeign {
    @GetMapping("/merchant/{id}")
    MerchantDto findById(@PathVariable(value = "id") String id);

    @GetMapping("/merchant/prod/{pid}/{mid}")
    ResponseMerchantDto findProductPrice(@PathVariable("pid") String pid, @PathVariable("mid") String mid);

    @RequestMapping(value = "/merchant/prod",method = {RequestMethod.PUT})
    void save(@RequestBody ResponseMerchantDto productDto);
}
