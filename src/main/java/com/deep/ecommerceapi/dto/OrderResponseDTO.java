package com.deep.ecommerceapi.dto;

import com.deep.ecommerceapi.entity.Product;
import com.deep.ecommerceapi.entity.User;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDTO {
    private Long orderId;
    private Double amount;
    private String shippingAddress;
    private Date orderDate;
    private String orderStatus;
    private Integer quantity;

}
