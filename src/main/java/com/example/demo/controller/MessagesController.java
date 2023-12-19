package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * A controller for sending messages to clients through firebase.
 */
@RestController
@RequestMapping("/api/messages")
public class MessagesController {

    private final FcmService fcmService;

    // Autowiring the FcmService using constructor injection
    @Autowired
    public MessagesController(FcmService fcmService) {
        this.fcmService = fcmService;
    }


    /**
     * The end point for sending the messages.
     */
    @PostMapping("/send")
    public ResponseEntity<String> sendNotificationToUser(
            @RequestParam String fcmToken,
            @RequestParam String title,
            @RequestParam String message) {

        // Calling the sendMessageToClient method that sends the messages.
        String response = fcmService.sendMessageToClient(fcmToken, title, message);

        // Return the response.
        return ResponseEntity.ok(response);
    }
}
