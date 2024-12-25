package com.project.journalApp.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.RedisTemplate;

// This class is used to interact with the redis server
@Service
@Slf4j
public class RedisService {
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public <T> T get(String key, Class<T> entityClass){ // entity class is a weather response class that we want to store in the redis
        try{
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(o.toString(), entityClass);
        }

        catch(Exception e){
            log.error("Exception", e);
            return null;
        }
    }


    public void set(String key, Object o, Long ttl){
        try{
            ObjectMapper mapper = new ObjectMapper();   
            String value = mapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS);
        }

        catch(Exception e){
            log.error("Exception", e);
        }
    }

    // If we give ttl -1, then we save data without expiry

}
