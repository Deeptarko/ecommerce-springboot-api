package com.deep.ecommerceapi.repository;

import com.deep.ecommerceapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    public List<Order>findAllByUserId(Long userId);
    public List<Order>findAllByProductId(Long productId);

    @Modifying
    @Query(value="update orders set order_status = ?1 where id = ?2",nativeQuery = true)
    public int updateStatusForOrder(String status, Long orderId);
}
