package com.example.hazelcastsample.cache.repos;

import com.example.hazelcastsample.cache.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepo extends MongoRepository<Student, String> {
}