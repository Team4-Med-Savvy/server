package com.example.merchant.service.impl;

import com.example.merchant.entity.Merchant;
import com.example.merchant.repository.MerchantRepository;
import com.example.merchant.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    MerchantRepository merchantRepository;

    @Override
    public Merchant select(String id) {
        return merchantRepository.findById(id).get();
    }

    @Override
    public void save(Merchant merchant) {
        merchantRepository.save(merchant);
    }

    @Override
    public void delete(String id) {
        merchantRepository.deleteById(id);
    }
}
