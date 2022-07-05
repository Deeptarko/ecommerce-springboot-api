package com.deep.ecommerceapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDTO {
    private Double amount;
    private String shippingAddress;
    private String orderStatus;
    private int quantity;
    private String username;
    private Long productId;
}
