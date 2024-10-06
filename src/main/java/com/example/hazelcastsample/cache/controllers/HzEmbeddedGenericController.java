package com.example.hazelcastsample.cache.controllers;

import com.example.hazelcastsample.cache.models.Student;
import com.example.hazelcastsample.cache.services.HzStudentGenericCacheService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
public class HzEmbeddedGenericController {

    private final HzStudentGenericCacheService studentService;
    public HzEmbeddedGenericController(HzStudentGenericCacheService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable() String id) {
        return studentService.getStudentById(id);
    }

    @PostMapping("/student/add")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/student/update")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable() String id) {
        studentService.deleteStudent(id);
    }

    @DeleteMapping("/student/clearCache")
    public void clearCache() {
        studentService.deleteAllStudents();
    }
}
