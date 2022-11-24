package com.exam.examportal.config;

import com.exam.examportal.entities.User;
import com.exam.examportal.service.impl.CustomUserDetailsServiceImpl;
import com.exam.examportal.service.impl.UserServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
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
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String username = null;
        String jwtToken = null;

        String requestHeaderToken = request.getHeader("Authorization");

        if(requestHeaderToken != null && requestHeaderToken.startsWith("Bearer ")){
            jwtToken = requestHeaderToken.substring(7);
            try{
            username = jwtUtil.extractUsername(jwtToken);
            }
            catch (ExpiredJwtException e){
                e.printStackTrace();
                System.out.println("Token has been expired");
            }
            // validate token
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            if(userDetails!=null && SecurityContextHolder.getContext().getAuthentication() == null){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else{
                System.out.println("Token is not valid and username is not found");
            }
        }

        // forward request...
        filterChain.doFilter(request,response);
    }
}
