package com.deep.ecommerceapi.controller;

import com.deep.ecommerceapi.dto.LoginRequestDTO;
import com.deep.ecommerceapi.entity.User;
import com.deep.ecommerceapi.service.UserService;
import com.deep.ecommerceapi.utils.JWTUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO request){
        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
        authenticationManager.authenticate(token);
        String jwtToken= jwtUtil.generateToken(request.getUsername());
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);

    }

    @PostMapping("user/save")
    public ResponseEntity<String> saveUser(@RequestBody User user){
        Long id=userService.saveUser(user);
        return new ResponseEntity<>("User with the "+id+" created",HttpStatus.CREATED);
    }
}


//{
//        "name":"Deep Roy",
//        "username":"deep6104",
//        "password":"1234",
//        "roles":["ADMIN","EMP"]
//}

//{
//        "name":"Anshu Kailash",
//        "username":"anshu",
//        "password":"1234",
//        "roles":["ADMIN","EMP"]
//        }
