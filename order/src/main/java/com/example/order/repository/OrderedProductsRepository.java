package com.example.order.repository;

import com.example.order.dto.OrderedProductsDto;
import com.example.order.entity.OrderedProducts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedProductsRepository extends CrudRepository<OrderedProducts,Long> {
}
