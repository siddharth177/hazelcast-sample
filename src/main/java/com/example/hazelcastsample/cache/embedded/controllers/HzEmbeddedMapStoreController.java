package com.example.hazelcastsample.cache.embedded.controllers;

import com.example.hazelcastsample.commons.models.Student;
import com.example.hazelcastsample.cache.embedded.services.HzStudentCacheService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache/mapStore/")
@Tag(name = "Embedded MapStore Cache Controller", description = "Apis' for demoing cache operations with hazelcast and mongoDb. Hazelcast handles data synchronization between persistence and cache.")
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
