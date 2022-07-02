package com.deep.ecommerceapi.service;

import com.deep.ecommerceapi.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    Long saveUser(User user);
}
