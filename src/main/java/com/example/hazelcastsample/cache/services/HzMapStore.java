package com.example.hazelcastsample.cache.services;

import com.example.hazelcastsample.cache.models.Student;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.MapLoaderLifecycleSupport;
import com.hazelcast.map.MapStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;

@Component
@Slf4j
public class HzMapStore implements MapStore<String, Student>, MapLoaderLifecycleSupport {

    @Override
    public void init(HazelcastInstance hazelcastInstance, Properties properties, String s) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void store(String s, Student student) {

    }

    @Override
    public void storeAll(Map<String, Student> map) {

    }

    @Override
    public void delete(String s) {

    }

    @Override
    public void deleteAll(Collection<String> collection) {

    }

    @Override
    public Student load(String s) {
        return null;
    }

    @Override
    public Map<String, Student> loadAll(Collection<String> collection) {
        return Map.of();
    }

    @Override
    public Iterable<String> loadAllKeys() {
        return null;
    }
}
