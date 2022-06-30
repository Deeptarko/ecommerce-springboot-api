package com.deep.ecommerceapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private Double amount;
    @Column(name="shipping_address")
    private String shippingAddress;
    @Column(name="order_date")
    private Date orderDate;
    @Column(name="order_status")
    private String orderStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_customer")
    private Customer customer;

}
