package com.example.hazelcastsample.cache.services;

import com.example.hazelcastsample.cache.models.Student;
import com.example.hazelcastsample.cache.repos.StudentRepo;
import com.hazelcast.map.MapStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class HzMapStore implements MapStore<String, Student> {
    private final StudentRepo studentRepo;

    @Autowired
    public HzMapStore(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public void store(String s, Student student) {
        studentRepo.save(student);
    }

    @Override
    public void storeAll(Map<String, Student> map) {
        studentRepo.saveAll(map.values());
    }

    @Override
    public void delete(String s) {
        studentRepo.deleteById(s);
    }

    @Override
    public void deleteAll(Collection<String> collection) {
        collection.forEach(studentRepo::deleteById);
    }

    @Override
    public Student load(String s) {
        return studentRepo.findById(s).orElse(null);
    }

    @Override
    public Map<String, Student> loadAll(Collection<String> collection) {
        return studentRepo.findAllById(collection).stream().collect(Collectors.toMap(Student::getId, s -> s));
    }

    @Override
    public Iterable<String> loadAllKeys() {
        return studentRepo.findAll().stream().map(Student::getId).collect(Collectors.toList());
    }
}
