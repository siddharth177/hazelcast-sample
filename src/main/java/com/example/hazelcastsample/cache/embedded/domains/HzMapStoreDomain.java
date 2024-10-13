package com.example.hazelcastsample.cache.embedded.domains;

import com.example.hazelcastsample.commons.models.Student;
import com.hazelcast.map.MapStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class HzMapStoreDomain implements MapStore<String, Student> {
    @Autowired
    private MongoTemplate mongoTemplate;
    private final String collectionName = "students";

    @Override
    public void store(String s, Student student) {
        mongoTemplate.save(student, collectionName);
    }

    @Override
    public void storeAll(Map<String, Student> map) {
        map.forEach(this::store);
    }

    @Override
    public void delete(String s) {
        mongoTemplate.remove(s, collectionName);
    }

    @Override
    public void deleteAll(Collection<String> collection) {
        collection.forEach(this::delete);
    }

    @Override
    public Student load(String s) {
        return mongoTemplate.findById(s, Student.class, collectionName);
    }

    @Override
    public Map<String, Student> loadAll(Collection<String> collection) {
        Map<String, Student> result = new HashMap<>();
        collection.forEach(key -> {
            Student entity = mongoTemplate.findById(key, Student.class, collectionName);
            if (entity != null) {
                result.put(key, entity);
            }
        });
        return result;
    }

    @Override
    public Iterable<String> loadAllKeys() {
        return mongoTemplate.findAll(Student.class, collectionName)
                .stream()
                .map(Student::getId)
                .toList();
    }
}
