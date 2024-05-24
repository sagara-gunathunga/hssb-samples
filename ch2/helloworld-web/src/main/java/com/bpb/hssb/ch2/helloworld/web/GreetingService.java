package com.bpb.hssb.ch2.helloworld.web;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

@Component
public class GreetingService {

    public String greet() {
        LocalTime now = LocalTime.now();
        int currentHour = now.getHour();
        if(currentHour < 12) {
            return "it’s a wonderful morning!";
        } else {
            return "it’s a wonderful afternoon!";
        }
    }
}
