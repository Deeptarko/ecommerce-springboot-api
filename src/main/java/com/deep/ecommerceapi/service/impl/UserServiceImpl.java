package com.deep.ecommerceapi.service.impl;

import com.deep.ecommerceapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // This is where you should fetch the user from database.
        // We keep it simple to focus on authentication flow.
        Map<String, String> users = new HashMap<>();
        users.put("martin", passwordEncoder.encode("123"));
        if (users.containsKey(username))
            return new User(username, users.get(username), new ArrayList<>());
        // if this is thrown, then we won't generate JWT token.
        throw new UsernameNotFoundException(username);
    }
}
