package com.example.demo.controller;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.example.demo.Test;
import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


/**
 * Define end points that uses the DB.
 */
@RestController
public class MainController {


    /**
     * The DB's repository.
     */
    @Autowired
    UserRepository userRepository;


    /**
     * The service that responsible to communicate with firebase.
     */
    FcmService fcmService;


    @Autowired
    public MainController(FcmService fcmService) {
        this.fcmService = fcmService;
    }


    /**
     * Add the given user to the DB.
     */
    @PostMapping("/AddUser")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        user.setLastTimeChecked(System.currentTimeMillis());
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    /**
     * Get the user with the given id.
     */
    @GetMapping("/GetUser/{id}")
    public User getUser(@PathVariable("id") String id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }


    /**
     * Get all the users that their name starts with the given prefix.
     * We use here an index (GSI) to search, so it suppose to be efficient.
     */
    @GetMapping("/GetUsersByPrefix/{prefix}")
    public List<User> getUsersByPrefix(@PathVariable("prefix") String prefix) {
        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<>();
        queryExpression.withIndexName("NameIndex")
                .withKeyConditionExpression("begins_with(name, :prefix)")
                .withExpressionAttributeValues(Collections.singletonMap(":prefix", new AttributeValue().withS(prefix)))
                .withConsistentRead(false); // GSI does not support consistent read

        return userRepository.findByNameStartsWith(prefix);
    }


    // todo: need to make this method better:

    /**
     * Get the location of the user with the given id.
     */
    @GetMapping("/GetUserLocation/{id}")
    public ResponseEntity<Location> getUserLocation(@PathVariable("id") String id) {
        Location location = fetchLocation(id);

        return ResponseEntity.ok(location);
    }


    @Async
    private Location fetchLocation(String id) {

        try {
            Optional<User> userResult = userRepository.findById(id);

            if (userResult.isEmpty())
                throw new ResourceNotFoundException("The requested user location was not found!");

            User user = userResult.get();
            if (UserUtils.isLocationValid(user.getLastTimeChecked())) {
                return new Location(user.getLat(), user.getLon());
            }

            // Sending a request to the other user (to update its location).
            System.out.println("sending request to another user!");
            fcmService.sendMessageToClient(user.getFcmToken(), "update_location", "");

            int tries = 3;

            while (!UserUtils.isLocationValid(user.getLastTimeChecked()) && tries > 0) {
                Thread.sleep(1000); // wait
                userResult = userRepository.findById(id);

                if (userResult.isEmpty())
                    throw new ResourceNotFoundException("The requested user location was not found!");

                user = userResult.get();
                tries -= 1;
            }

            return new Location(user.getLat(), user.getLon());

        } catch (InterruptedException e) {  // todo: deal with this case.
            return new Location(0D, 0D);
        }
    }


    /**
     * Update the location of the given user in the database.
     */
    @PostMapping("/UpdateUserLocation/{id}")
    public User updateUserLocation(@PathVariable("id") String id, @RequestBody Location location) {

        System.out.println("Update the location of this user: " + id + "with this location: " + location);

        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setLocation(location);
        user.setLastTimeChecked(System.currentTimeMillis());

        return userRepository.save(user);
    }


    /**
     * Get the user with the given id.
     */
    @GetMapping("/GetAllUsers")
    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }


    /**
     * The end point for updating the firebase token of a user, with the given firebaseToken.
     * The id - is the id of the user.
     */
    @PostMapping("/UpdateFirebaseToken/{id}")
    public ResponseEntity<Void> updateToken(@PathVariable("id") String id, @RequestParam String firebaseToken) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setFcmToken(firebaseToken);

        userRepository.save(user);

        return ResponseEntity.ok().build();
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
    public String test1() {
        return "hello!";
    }

}


