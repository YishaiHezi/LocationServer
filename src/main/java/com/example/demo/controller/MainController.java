package com.example.demo.controller;

import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.example.demo.Test;
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
     * Used to send messages to the client with firebase.
     */
    private final FcmService fcmService;


    @Autowired
    MainController(FcmService fcmService){
        this.fcmService = fcmService;
    }


    /**
     * Get the user with the given id.
     */

    // todo: this method should be implemented using background thread pool.

    @GetMapping("/GetUser/{id}")
    public User getUser(@PathVariable("id") String id) {
        User user =  userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

//        long time = user.getLastTimeChecked();
//
//        if (System.currentTimeMillis() - time > 60000){
//            // todo: try to update the time by calling the client.
//
//            String fcmToken = user.getFcmToken();
//
//            fcmService.sendMessageToClient(fcmToken, "postLocation", "");
//
//        }
//        else {
//            return user;
//        }

        String fcmToken = user.getFcmToken();
        fcmService.sendMessageToClient(fcmToken, "postLocation", "");


        return user;
    }


    /**
     * Save the given user in the database.
     */
    @PostMapping("/AddUser")
    public User addUser(@RequestBody User user) {
        return userRepository.save(Encryptor.encryptUser(user));
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


