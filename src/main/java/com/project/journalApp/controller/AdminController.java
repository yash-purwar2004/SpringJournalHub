package com.project.journalApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.journalApp.entity.User;
import com.project.journalApp.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/admin")
@Tag(name = "Admin APIs", description = "Create and Read all users")
public class AdminController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    @Operation(summary = "Get all users")
    public ResponseEntity<?> getAllUsers(){
        List<com.project.journalApp.entity.User> all = userService.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    @Operation(summary = "Create an admin user")
    public void createUser(@RequestBody User user) {
       userService.saveAdmin(user);
    }
    
}
