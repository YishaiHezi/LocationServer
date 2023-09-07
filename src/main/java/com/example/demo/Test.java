package com.example.demo;

import org.springframework.boot.SpringApplication;

import java.time.LocalTime;

public class Test {


    public static void main(String[] args) {
        LocalTime now = LocalTime.now();
        int minutes = now.getMinute();


        System.out.println(minutes);
    }

}
