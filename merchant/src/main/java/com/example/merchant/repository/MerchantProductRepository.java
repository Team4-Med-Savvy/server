package com.example.merchant.repository;

import com.example.merchant.entity.MerchantProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantProductRepository extends CrudRepository<MerchantProduct,Long> {

}
