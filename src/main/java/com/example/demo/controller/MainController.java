package com.example.demo.controller;

import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.example.demo.Test;
import com.example.demo.model.Location;
import com.example.demo.model.User;
import com.example.demo.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Define end points that uses the DB.
 */
@RestController
public class MainController {


    @Autowired
    UserRepository userRepository;


    /**
     * Get the user with the given id.
     */



    @GetMapping("/GetUser/{id}")
    public User getUser(@PathVariable("id") String id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }


    // todo: this method should be implemented using background thread pool.
    // todo: need to think how it should be done. because if the requested user location is not updated -
    // todo: we need to send an fcm message to the user to update its location, and then wait until the data
    // todo: in the DB will be updated.

    @GetMapping("/GetUserLocation/{id}")
    public Location getUserLocation(@PathVariable("id") String id){

        // todo: implement
        return new Location(0D,0D); // dummy location
    }


    /**
     * Update the location of the given user in the database.
     */
    @PostMapping("/UpdateUserLocation/{id}")
    public User updateUserLocation(@PathVariable("id") String id, @RequestBody Location location) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setLocation(location);

        return userRepository.save(user);
    }


    /**
     * Get the user with the given id.
     */
    @GetMapping("/GetAllUsers")
    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }


    // todo: these methods can be deleted:

    /**
     * This method is only for testing.
     */
    @GetMapping("/Test")
    public ResponseEntity<Test> getTest() {
        return ResponseEntity.ok(new Test("123", "name1", 123D, 123D));
    }


    @GetMapping("/test1")
    public String test1(){
        return "hello!";
    }

}


