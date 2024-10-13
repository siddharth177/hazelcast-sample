package com.example.hazelcastsample.cache.embedded.services;

public interface CacheService<K, V> {
    V save(K key, V value);
    V get(K key);
    void remove(K key);
    void clear();
    boolean contains(K key);
}