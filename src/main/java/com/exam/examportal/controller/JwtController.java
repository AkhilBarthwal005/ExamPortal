package com.exam.examportal.controller;

import com.exam.examportal.config.CustomUser;
import com.exam.examportal.config.JwtUtil;
import com.exam.examportal.entities.User;
import com.exam.examportal.model.JwtRequest;
import com.exam.examportal.model.JwtResponse;
import com.exam.examportal.service.impl.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsService;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest){
        System.out.println(jwtRequest);
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
        }
        catch (UsernameNotFoundException e){
            e.printStackTrace();
            System.out.println("UserName Not found Exception");
        }
        catch (BadCredentialsException e){
            e.printStackTrace();
            System.out.println("Bad Credentials");
        }

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());

        String token = jwtUtil.generateToken(userDetails);

        System.out.println("Token : " + token);

        return ResponseEntity.ok(new JwtResponse(token));

    }

    @GetMapping("/current-user")
    public CustomUser getCurrentUserDetails(Principal principal){
        return (CustomUser) customUserDetailsService.loadUserByUsername(principal.getName());
    }
}
