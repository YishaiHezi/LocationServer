package com.example.demo.controller;

import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Define end points that uses the DB.
 */
@RestController
public class RepositoryController {


    @Autowired
    UserRepository userRepository;


    /**
     * Get the user with the given id.
     */
    @GetMapping("/User/{id}")
    public User getUser(@PathVariable("id") String id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }


    /**
     * Save the given user in the database.
     */
    @PostMapping("/User")
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

}

