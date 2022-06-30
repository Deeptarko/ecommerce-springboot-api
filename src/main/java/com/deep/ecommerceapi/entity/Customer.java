package com.deep.ecommerceapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String email;
    private String password;
    @Column(name = "full_name")
    private String fullName;
    @Column(name="default_shipping_address")
    private String defaultShippingAddress;
    private String country;
    @Column(name="phone_number")
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private List<Order> orderList;

    public void addOrder(Order order){
        orderList.add(order);
    }
}
