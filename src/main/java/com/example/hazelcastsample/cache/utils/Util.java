package com.example.hazelcastsample.cache.utils;

import org.springframework.stereotype.Component;

@Component
public class Util {
    public static void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
