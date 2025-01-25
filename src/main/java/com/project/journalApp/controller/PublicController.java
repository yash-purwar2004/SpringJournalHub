package com.project.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.journalApp.entity.User;
import com.project.journalApp.service.UserDetailsServiceImpl;
import com.project.journalApp.service.UserService;
import com.project.journalApp.utils.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/public")
@Tag(name = "Public APIs", description = "Sign up and login")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;



    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    @Operation(summary = "Sign up to the application")
    public void signup(@RequestBody com.project.journalApp.dto.UserDTO user) {
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setSentimentAnalysis(user.isSentimentAnalysis());
        userService.saveNewUser(newUser);
    }

    @PostMapping("/login")
    @Operation(summary = "Login to the application")
    public ResponseEntity<String> login(@RequestBody User user) {
        try{
            authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName()); 
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } 
        
        catch (Exception e) {
            log.error("Exception occured while createAuthenticationToken", e);
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
        
    }
}
