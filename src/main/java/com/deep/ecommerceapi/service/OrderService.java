package com.deep.ecommerceapi.service;

import com.deep.ecommerceapi.dto.OrderRequestDTO;
import com.deep.ecommerceapi.dto.OrderResponseDTO;
import com.deep.ecommerceapi.entity.Order;

import java.util.List;

public interface OrderService {

    public String saveOrder(OrderRequestDTO orderRequest);
    public List<OrderResponseDTO> getAllOrders();

    public List<OrderResponseDTO> getOrdersByUserId(Long userId);

    public List<OrderResponseDTO> getOrderByProductId(Long productId);
}
