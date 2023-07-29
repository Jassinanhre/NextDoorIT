package com.inn.nextDoorIt.JWT;

import com.inn.nextDoorIt.service.TokenBlacklistService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtLogoutFilter extends OncePerRequestFilter implements Filter {

    private String secret = "NextDoorIT@2023";
    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomerUsersDetailsService service;
    private Jws<Claims> claimsJws = null;
    private String tokenId;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getServletPath().matches("/user/logout")) { // for the logout request
            String authorizarionHeader = request.getHeader("Authorization");
            String token = null;

            // in below code we are marking the token as expired
            if (authorizarionHeader != null && authorizarionHeader.startsWith("Bearer ")) {
                token = authorizarionHeader.substring(7);
                tokenBlacklistService.addToBlacklist(token); // IF USER HITS LOGOUT ACCOUNT THEN ADD ITS TOKEN IN BLACKLIST SIMPLY
            }
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }


    }
}
