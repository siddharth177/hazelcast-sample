package com.example.hazelcastsample.cache.services;

public interface CacheService<V> {
    V save(String key, V value);
    V get(String key);
    void remove(String key);
    void clear();
    boolean contains(String key);
}