package com.project.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.journalApp.entity.User;


public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);

    void deleteByUserName(String userName);
}

// findByUsername: Spring Data derives this query method automatically based on its name. It generates a query to find a User document where the username 
// field matches the provided String username parameter.
// Return type: Returns a User object if a match is found in the database.