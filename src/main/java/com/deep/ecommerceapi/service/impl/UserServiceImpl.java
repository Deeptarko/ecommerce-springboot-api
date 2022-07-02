package com.deep.ecommerceapi.service.impl;

import com.deep.ecommerceapi.entity.User;
import com.deep.ecommerceapi.repository.UserRepository;
import com.deep.ecommerceapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


    public Long saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Saving user");
        return userRepository.save(user).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User>user=findByUsername(username);
        if(user.isPresent()){
            User newUser=user.get();
            return new org.springframework.security.core.userdetails.User(
                    newUser.getUsername(),
                    newUser.getPassword(),
                    newUser.getRoles().stream().map((role)->new SimpleGrantedAuthority(role)).collect(Collectors.toList())
            );

        }
        throw new UsernameNotFoundException("User not found");
    }

    @Override
    public Optional<com.deep.ecommerceapi.entity.User> findByUsername(String username) {
        log.info("Getting user with the username "+username);
        return userRepository.findByUsername(username);
    }
}
