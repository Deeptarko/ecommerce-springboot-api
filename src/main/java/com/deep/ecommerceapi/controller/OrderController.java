package com.deep.ecommerceapi.controller;

import com.deep.ecommerceapi.dto.OrderRequestDTO;
import com.deep.ecommerceapi.dto.OrderResponseDTO;
import com.deep.ecommerceapi.entity.Order;
import com.deep.ecommerceapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //Save Order
    @PostMapping("/save")
    public ResponseEntity<String>saveOrder(@RequestBody OrderRequestDTO orderRequest){
        String msg=orderService.saveOrder(orderRequest);
        return new ResponseEntity<>(msg,HttpStatus.CREATED);
    }


    //Get All Orders
    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDTO>>getAllOrders(){

        List<OrderResponseDTO>orderList=orderService.getAllOrders();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    //Get Order By UserId

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrderByUserId(@PathVariable Long userId){
        List<OrderResponseDTO> orderList=orderService.getOrdersByUserId(userId);
        return new ResponseEntity<>(orderList, HttpStatus.OK);

    }

    //Get Orders By ProductId
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrderByProductId(@PathVariable Long productId){
        List<OrderResponseDTO> orderList=orderService.getOrderByProductId(productId);
        return new ResponseEntity<>(orderList, HttpStatus.OK);

    }

}
