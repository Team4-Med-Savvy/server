package com.example.product.service.feignservice;

import com.example.product.dto.MerchantDto;
import com.example.product.dto.MerchantProductDto;
import com.example.product.dto.ResponseMerchantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@FeignClient(name="merchant")
public interface ProductFeignService {
    @RequestMapping(value = "merchant/{id}", method = RequestMethod.GET)
    MerchantDto findById(@PathVariable("id") String id);

    @GetMapping(value = "/merchant/prod/{pid}/{mid}")
    ResponseMerchantDto findProductPrice(@PathVariable(value = "pid") String pid, @PathVariable(value = "mid") String mid);



}
