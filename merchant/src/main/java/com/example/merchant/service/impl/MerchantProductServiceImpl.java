package com.example.merchant.service.impl;

import com.example.merchant.entity.MerchantProduct;
import com.example.merchant.repository.MerchantProductRepository;
import com.example.merchant.service.MerchantProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantProductServiceImpl implements MerchantProductService {

    @Autowired
    MerchantProductRepository merchantProductRepository;


    @Override
    public MerchantProduct select(Long id) {
        return merchantProductRepository.findById(id).get();
    }

    @Override
    public void save(MerchantProduct merchantProduct) {
        merchantProductRepository.save(merchantProduct);

    }

    @Override
    public void delete(Long id) {
        merchantProductRepository.deleteById(id);
    }
}
