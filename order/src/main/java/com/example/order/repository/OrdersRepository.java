package com.example.order.repository;

import com.example.order.entity.OrderedProducts;
import com.example.order.entity.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends CrudRepository<Orders,Long> {
    List<Orders> findByUserId(String id);

}
