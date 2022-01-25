package com.example.merchant.service;

import com.example.merchant.entity.Merchant;

public interface MerchantService {
    Merchant select(Long id);
    void save(Merchant merchant);
    void delete(Long id);
}
