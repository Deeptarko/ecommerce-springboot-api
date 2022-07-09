package com.deep.ecommerceapi.service.impl;

import com.deep.ecommerceapi.dto.OrderRequestDTO;
import com.deep.ecommerceapi.dto.OrderResponseDTO;
import com.deep.ecommerceapi.entity.Order;
import com.deep.ecommerceapi.entity.Product;
import com.deep.ecommerceapi.entity.User;
import com.deep.ecommerceapi.exception.OrderException;
import com.deep.ecommerceapi.repository.OrderRepository;
import com.deep.ecommerceapi.repository.ProductRepository;
import com.deep.ecommerceapi.repository.UserRepository;
import com.deep.ecommerceapi.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public OrderResponseDTO saveOrder(OrderRequestDTO orderRequest) {
        log.info("Saving Order");
        log.info("Fetching user with the username {}",orderRequest.getUsername());
        Optional<User> user=userRepository.findByUsername(orderRequest.getUsername());
        log.info("Fetching product with the id {}",orderRequest.getProductId());
        Optional<Product> product=productRepository.findById(orderRequest.getProductId());
        if(user.isPresent() && product.isPresent()){
            Order order=Order.builder()
                    .amount(orderRequest.getAmount())
                    .user(user.get())
                    .quantity(orderRequest.getQuantity())
                    .product(product.get())
                    .shippingAddress(orderRequest.getShippingAddress())
                    .orderStatus(orderRequest.getOrderStatus())
                    .build();
            Order savedOrder=orderRepository.save(order);
            OrderResponseDTO orderResponse=mapToDTO(savedOrder);
            return orderResponse;
        }

       throw new OrderException(HttpStatus.BAD_REQUEST,"Either user or product not present");
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        log.info("Fetching all the orders");
        List<Order>orderList=orderRepository.findAll();

        List<OrderResponseDTO>newOrderList=orderList.stream().map((order)->mapToDTO(order)).collect(Collectors.toList());

        return newOrderList;
    }

    @Override
    public List<OrderResponseDTO> getOrdersByUserId(Long userId) {
        log.info("Fetching orders with the user id: {}",userId);
        List<Order> orderList= orderRepository.findAllByUserId(userId);
        List<OrderResponseDTO>newOrderList=orderList.stream().map((order)->mapToDTO(order)).collect(Collectors.toList());
        return newOrderList;
    }

    @Override
    public List<OrderResponseDTO> getOrderByProductId(Long productId) {
        log.info("Fetching orders with the product id: {}",productId);
        List<Order> orderList= orderRepository.findAllByProductId(productId);
        List<OrderResponseDTO>newOrderList=orderList.stream().map((order)->mapToDTO(order)).collect(Collectors.toList());
        return newOrderList;
    }

    @Override
    public String deleteOrderByOrderId(Long orderId) {
        log.info("Deleting order with the id {}",orderId);
        orderRepository.deleteById(orderId);
        return "Order deleted successfully";
    }

    @Override
    public String updateOrderByOrderId(Long orderId) {
        Optional<Order> order=orderRepository.findById(orderId);
        if(order.isPresent()){
            if(order.get().getOrderStatus().equals("PLACED")){
                orderRepository.updateStatusForOrder("DISPATCHED",orderId);
            }else {
                orderRepository.updateStatusForOrder("DELIVERED",orderId);
            }
            return "Order with the id "+orderId+" successfully updated";
        }
        throw new OrderException(HttpStatus.BAD_REQUEST,"Order not found with the given id");

    }


    //Utility Method-Entity To DTO
    public OrderResponseDTO mapToDTO(Order order){

        OrderResponseDTO orderResponseDTO=OrderResponseDTO.builder()
                .orderDate(order.getOrderDate())
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .quantity(order.getQuantity())
                .amount(order.getAmount())
                .shippingAddress(order.getShippingAddress())
                .build();

        return orderResponseDTO;
    }
}
