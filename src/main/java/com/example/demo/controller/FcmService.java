package com.example.demo.controller;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.stereotype.Service;


/**
 * Here we send a message to a client (identified by the fcmToken) using firebase.
 */
@Service
public class FcmService {

    public String sendMessageToClient(String fcmToken, String title, String message) {
        Message msg = Message.builder()
                .putData("title", title)
                .putData("body", message)
                .setToken(fcmToken)
                .build();

        String messageId = null; // Returns the message ID

        try {
            messageId = FirebaseMessaging.getInstance().send(msg);
        } catch (FirebaseMessagingException e) {
            System.err.println("***Failed to send notification from firebase!***");
        }


        return messageId;
    }
}