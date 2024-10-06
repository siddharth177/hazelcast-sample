package com.example.hazelcastsample.cache.controllers;

import com.example.hazelcastsample.cache.models.Student;
import com.example.hazelcastsample.cache.services.HzStudentCacheService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache/mapStore/")
public class HzEmbeddedMapStoreController {
    private final HzStudentCacheService hzStudentCacheService;

    public HzEmbeddedMapStoreController(HzStudentCacheService hzStudentCacheService) {
        this.hzStudentCacheService = hzStudentCacheService;
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable() String id) {
        return hzStudentCacheService.getStudentById(id);
    }

    @PostMapping("/student/add")
    public Student addStudent(@RequestBody Student student) {
        return hzStudentCacheService.addStudent(student);
    }

    @PutMapping("/student/update")
    public Student updateStudent(@RequestBody Student student) {
        return hzStudentCacheService.updateStudent(student);
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable() String id) {
        hzStudentCacheService.deleteStudent(id);
    }

    @DeleteMapping("/student/clearCache")
    public void clearCache() {
        hzStudentCacheService.deleteAllStudents();
    }
}
