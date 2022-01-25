package com.example.merchant.service;

import com.example.merchant.entity.MerchantProduct;

public interface MerchantProductService {
    MerchantProduct select(Long id);
    void save(MerchantProduct merchantProduct);
    void delete(Long id);
}
