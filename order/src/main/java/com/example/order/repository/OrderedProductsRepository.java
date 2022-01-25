package com.example.order.repository;

import com.example.order.entity.OrderedProducts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedProductsRepository extends CrudRepository<OrderedProducts,Long> {
}
