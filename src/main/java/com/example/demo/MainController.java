package com.example.demo;


import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * The main controller.
 */
@RestController
public class MainController {


    Map<String, Double> locations = new HashMap<>() {{
        put("Moshe", 1D);
        put("Yochai", 2D);
        put("Ariel", 3D);
        put("Moriah", 4D);
    }};

    long lastTimeMapCleared = System.currentTimeMillis();


    /**
     * Api end-point for getting a user's location.
     */
    @GetMapping("/getUserLocation/{name}")
    public UserLocation getUserLocation(@PathVariable String name) {
        maybeClearMap();
        return new UserLocation(name, locations.getOrDefault(name, null));
    }


    /**
     * Api end-point for setting a user's location.
     */
    @PostMapping("/SendUserLocation")
    public String postUserLocation(@RequestBody UserLocation user) {
        maybeClearMap();
        locations.put(user.getName(), user.getLocation());
        return "Thanks! the user: " + user.getName() + " and its location accepted!";
    }


    public void maybeClearMap() {
        if (System.currentTimeMillis() - lastTimeMapCleared > 600000) {
            locations.clear();
            lastTimeMapCleared = System.currentTimeMillis();
        }
    }


    public static class UserLocation {
        private final String name;
        private final Double location;

        UserLocation(@NonNull String name, @Nullable Double location){
            this.name = name;
            this.location = location;
        }

        // Getter and Setter methods
        public String getName() {
            return name;
        }

        public Double getLocation() {
            return location;
        }

    }

}
