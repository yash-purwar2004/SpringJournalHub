package com.project.journalApp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.project.journalApp.entity.User;

// Criteria is basically a rules from which we can judge the things


public class UserRepositoryImpl {
    
    @Autowired
    private MongoTemplate mongoTemplate;
    // mongo template is a class provided by spring data mongoDB
    // It provides a template to interact with database

    public List<User> getUserForSA(){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        List<User>users =  mongoTemplate.find(query, User.class);
        return users;
    }

}
