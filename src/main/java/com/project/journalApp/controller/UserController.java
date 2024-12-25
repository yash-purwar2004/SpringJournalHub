package com.project.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.journalApp.apiResponse.WeatherResponse;
import com.project.journalApp.entity.User;
import com.project.journalApp.repository.UserRepository;
import com.project.journalApp.service.UserService;
import com.project.journalApp.service.WeatherService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        // Retrieve the authenticated user's details from the Security Context
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Ensure the authentication object is valid
        if (authentication == null || authentication.getName() == null) {
            return new ResponseEntity<>("Unauthorized access", HttpStatus.UNAUTHORIZED);
        }
        String authenticatedUserName = authentication.getName(); // Extract authenticated username
        // Fetch the user from the database
        User userInDb = userService.findByUserName(authenticatedUserName);
        if (userInDb == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword()); // Ensure password is hashed before saving
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{city}")
    public ResponseEntity<?> greeting(@PathVariable String city) {
    org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    try {
        // Fetch weather data
        WeatherResponse weatherResponse = weatherService.getWeather(city);

        // Check if weatherResponse or its current object is null
        if (weatherResponse == null || weatherResponse.getCurrent() == null) {
            return new ResponseEntity<>("Weather data is unavailable for city: " + city, HttpStatus.NOT_FOUND);
        }

        // Get the "feels like" value
        int feelsLike = weatherResponse.getCurrent().getFeelslike();
        return new ResponseEntity<>("Hi " + authentication.getName() + ", Weather feels like " + feelsLike, HttpStatus.OK);

    } catch (Exception e) {
        // Handle any unexpected errors
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
}
