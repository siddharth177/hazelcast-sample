package com.example.hazelcastsample.commons.utils;

import lombok.Getter;

@Getter
public enum Caches {
    STUDENT_CACHE("student-cache");

    private final String cacheName;
    Caches(String name) {
        this.cacheName = name;
    }
}