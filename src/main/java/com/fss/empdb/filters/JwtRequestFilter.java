package com.fss.empdb.filters;

import com.fss.empdb.domain.User;
import com.fss.empdb.service.CustomUserDetailsService;
import com.fss.empdb.service.UsersService;
import com.fss.empdb.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    UsersService usersService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;
        Long userId = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            //username = jwtUtil.extractUsername(jwt);
            final Claims claims = jwtUtil.extractAllClaims(jwt);
            userId = Long.parseLong((String)claims.getSubject());
            username = (String)claims.get("userId");
            System.out.println("username userId :"+userId+"username ::"+username);
        }


        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("before usersService :"+userId);
            // doesn't have userId parameter
            //UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            User userDetails = usersService.userById(userId);
            System.out.println("Inside usersService :"+userDetails.getUserName());
            if (jwtUtil.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

}
