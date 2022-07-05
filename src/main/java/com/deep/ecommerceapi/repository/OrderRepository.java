package com.deep.ecommerceapi.repository;

import com.deep.ecommerceapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    public List<Order>findAllByUserId(Long userId);
    public List<Order>findAllByProductId(Long productId);
}
