package com.example.hazelcastsample.cache.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private String id;
    private String name;
    private String course;
    private List<Student> students;
}
