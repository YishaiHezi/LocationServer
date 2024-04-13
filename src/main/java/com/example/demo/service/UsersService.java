////package com.example.demo.service;
//
//
//import com.example.demo.controller.FcmService;
//import com.example.demo.model.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.lang.NonNull;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.Executor;
//
//
//// todo: maybe delete this? this is just a service for all the operations that are related to users.
//
//
///**
// * Service used to do operations related to users details.
// */
//@Service
//public class UsersService {
//
//
//    @Autowired
//    UserRepository userRepository;
//
//
//    /**
//     * Here - Spring inject the custom thread pool bean defined in AsyncConfig to the Executor.
//     */
//    @Autowired
//    @Qualifier("customThreadPool") // Specify the bean name
//    private Executor customThreadPool;
//
//
//    private final FcmService fcmService;
//
//
//    @Autowired
//    public UsersService(FcmService fcmService){
//        this.fcmService = fcmService;
//
//    }
//
//
////
////    @Async("customThreadPool")
////    public Location getUpdatedUserLocation(@NonNull String id) {
////
////    }
//
//    public void updateUserLocation(@NonNull String fcmToken){
//        fcmService.sendMessageToClient(fcmToken, "update_location", "");
//    }
//
//
//}
