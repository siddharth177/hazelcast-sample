package com.example.hazelcastsample.cache.embedded.repos;

import com.example.hazelcastsample.commons.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepo extends MongoRepository<Student, String> {
}