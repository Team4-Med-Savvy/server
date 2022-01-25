package com.example.merchant.controller;

import com.example.merchant.dto.MerchantDto;
import com.example.merchant.entity.Merchant;
import com.example.merchant.service.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/merchant")
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    @GetMapping(value = "/{idFromMerchant}")
    MerchantDto select(@PathVariable(value="idFromMerchant") Long id){
        Merchant merchant=merchantService.select(id);
        MerchantDto merchantDto=createDTOFromEntity(merchant);
        return merchantDto;
    }

    @RequestMapping(method ={RequestMethod.POST,RequestMethod.PUT})
    void save(@RequestBody MerchantDto merchantDto)
    {
        Merchant merchant=createEntityFromDto(merchantDto);
        merchantService.save(merchant);
    }

    @DeleteMapping(value="/{idFromMerchant1}")
    void delete(@PathVariable(value="idFromMerchant1") Long id){
        merchantService.delete(id);
    }

    public Merchant createEntityFromDto(MerchantDto dto){

        Merchant merchant=new Merchant();
        BeanUtils.copyProperties(dto,merchant);

        return merchant;
    }

    public MerchantDto createDTOFromEntity(Merchant entity){
        MerchantDto merchantDto=new MerchantDto();
        BeanUtils.copyProperties(entity,merchantDto);
        return merchantDto;
    }

}