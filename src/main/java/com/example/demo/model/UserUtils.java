package com.example.demo.model;


/**
 * Utils methods that related to the User.
 */
public class UserUtils {


    /**
     * Returns whether the location of the user is valid - which means that it was recorded in the
     * last 5 minutes.
     */
    public static boolean isLocationValid(long lastTimeChecked) {
        return System.currentTimeMillis() - lastTimeChecked < 10000; // This is 10 seconds.
    }


}
