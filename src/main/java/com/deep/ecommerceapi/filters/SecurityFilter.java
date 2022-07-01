package com.deep.ecommerceapi.filters;

import com.deep.ecommerceapi.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Read token from the authorization header
        String token=request.getHeader("Authorization");

        if(token!=null){
            String username=jwtUtil.getUsername(token);

            //Username should not be empty
            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                User user=(User) userDetailsService.loadUserByUsername(username);
                boolean isValid= jwtUtil.validateToken(token,username);
                if(isValid){
                    UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),user.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    filterChain.doFilter(request,response);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
