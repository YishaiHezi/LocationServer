package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalTime;

@RestController
public class HelloController {


    Map<String, Double> locations = new HashMap<>() {{
        put("Moshe", 1D);
        put("Yochai", 2D);
        put("Ariel", 3D);
        put("Moriah", 4D);
    }};

    @GetMapping("/getUserLocation/{name}")
    public String getUserLocation(@PathVariable String name) {
        maybeClearMap();
        if (locations.containsKey(name))
            return "The location of " + name + " is: " + locations.get(name);

        else
            return "The location of " + name + " is unknown!";
    }


    @PostMapping("/SendUserLocation")
    public String postUserLocation(@RequestBody UserLocation user) {
        maybeClearMap();
        locations.put(user.getName(), user.getLocation());
        return "Thanks! the user: " + user.getName() + " and its location accepted!";
    }

    public void maybeClearMap(){
        LocalTime now = LocalTime.now();
        int minutes = now.getMinute();

        if (minutes % 10 == 0) {
            locations.clear();
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
