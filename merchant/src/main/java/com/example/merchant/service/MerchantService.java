package com.example.merchant.service;

import com.example.merchant.entity.Merchant;

public interface MerchantService {
    Merchant select(String id);
    void save(Merchant merchant);
    void delete(String id);
}
