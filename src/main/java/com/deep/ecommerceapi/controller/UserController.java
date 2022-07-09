package com.deep.ecommerceapi.controller;

import com.deep.ecommerceapi.dto.LoginRequestDTO;
import com.deep.ecommerceapi.dto.LoginResponseDTO;
import com.deep.ecommerceapi.entity.User;
import com.deep.ecommerceapi.service.UserService;
import com.deep.ecommerceapi.utils.JWTUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@Slf4j
public class UserController {

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request){
        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
        log.info(request.getUsername()+":"+request.getPassword());
        Authentication auth=authenticationManager.authenticate(token);
        String jwtToken= jwtUtil.generateToken(request.getUsername());
        List<GrantedAuthority>roles=(List)auth.getAuthorities();
        List<String>rolesResponse=roles.stream().map((authority)->authority.getAuthority()).collect(Collectors.toList());
        LoginResponseDTO response=new LoginResponseDTO(jwtToken,auth.getName(),rolesResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("user/save")
    public ResponseEntity<String> saveUser(@RequestBody User user){
        Long id=userService.saveUser(user);
        return new ResponseEntity<>("User with the "+id+" created",HttpStatus.CREATED);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> test(Authentication authentication){
        return new ResponseEntity<>("Hello",HttpStatus.OK);
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
// }
