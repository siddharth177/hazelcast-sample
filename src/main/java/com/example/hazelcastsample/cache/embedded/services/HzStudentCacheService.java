package com.example.hazelcastsample.cache.embedded.services;

import com.example.hazelcastsample.cache.embedded.domains.HzCacheDomain;
import com.example.hazelcastsample.commons.models.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HzStudentCacheService {

    private final HzCacheDomain<String, Student> studentCacheDomain;

    public HzStudentCacheService(@Qualifier("HzEmbeddedStudentCacheDomain") HzCacheDomain<String, Student> studentCacheDomain) {
        this.studentCacheDomain = studentCacheDomain;
    }

    public Student getStudentById(String id) {
        log.info("retrieving student with id {}", id);
        return studentCacheDomain.get(id);
    }

    public Student addStudent(Student student) {
        log.info("saving student {}", student);
        return studentCacheDomain.save(student.getId(), student);
    }

    public Student updateStudent(Student student) {
        log.info("updating student {}", student);
        return studentCacheDomain.save(student.getId(), student);
    }

    public void deleteStudent(String id) {
        log.info("deleting student with id {}", id);
        studentCacheDomain.remove(id);
    }

    public void deleteAllStudents() {
        log.info("deleting all students");
        studentCacheDomain.clear();
    }

}