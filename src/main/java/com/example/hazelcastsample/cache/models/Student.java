package com.example.hazelcastsample.cache.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("students")
public class Student {
    @Id
    private String id;
    private String name;
    private int age;
    private List<String> courses;
}
