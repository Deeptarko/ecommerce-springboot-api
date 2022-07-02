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
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String username;
    private String password;
    @Column(name="default_shipping_address")
    private String defaultShippingAddress;
    private String country;
    @Column(name="phone_number")
    private String phoneNumber;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="roles_table",joinColumns = @JoinColumn(name="id"))
    private Set<String>roles;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Order> orderList;

    public void addOrder(Order order){
        orderList.add(order);
    }

}


/*
{
        "name":"Deep Roy",
        "username":"deep6104",
        "password":"1234",
        "defaultShippingAddress": "Delhi",
        "country":"India"
        "phoneNumber":"4846454545464"
        "roles":["ADMIN","EMP"]
}
*/

