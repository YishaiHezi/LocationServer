package com.example.demo;

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
    public String getUserLocation(@PathVariable String name) {
        maybeClearMap();
        if (locations.containsKey(name))
            return "The location of " + name + " is: " + locations.get(name);

        else
            return "The location of " + name + " is unknown!";
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
        private String name;
        private Double location;

        // Getter and Setter methods
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getLocation() {
            return location;
        }

        public void setLocation(Double location) {
            this.location = location;
        }
    }

}
