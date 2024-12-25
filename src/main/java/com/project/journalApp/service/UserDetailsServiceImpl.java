package com.project.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.journalApp.entity.User;
import com.project.journalApp.repository.UserRepository;

//The UserDetailsServiceImpl class is a custom implementation of the UserDetailsService interface provided by Spring Security. 
// The purpose of this class is to load user-specific data (like username, password, and roles) from the database during the
//  authentication process. This data is then used by Spring Security to validate and authenticate the user.
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
// UserDetailsService is a core interface in Spring Security that is responsible for fetching user details based on the username
    @Autowired
    private UserRepository userRepository;
    
    // This method is called by Spring Security when a user tries to authenticate
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + userName);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRoles().toArray(new String[0]))
                .build();
    }
}
