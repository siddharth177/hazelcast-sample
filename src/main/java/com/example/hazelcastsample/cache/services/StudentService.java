package com.example.hazelcastsample.cache.services;

import com.example.hazelcastsample.cache.domains.CacheDomain;
import com.example.hazelcastsample.cache.models.Student;
import com.example.hazelcastsample.cache.repos.StudentRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static com.example.hazelcastsample.cache.utils.Util.delay;

@Service
@Slf4j
public class StudentService {

    private CacheDomain<Student> studentCacheDomain;
    private StudentRepo studentRepo;

    public StudentService(@Qualifier("studentCacheDomain") CacheDomain<Student> studentCacheDomain, StudentRepo studentRepo) {
        this.studentCacheDomain = studentCacheDomain;
        this.studentRepo = studentRepo;
    }

    public Student getStudentById(String id) {
        log.info("retrieving student with id {}", id);
        Student student = studentCacheDomain.get(id);
        if(student == null) {
            log.info("student with id {} not found in the cache", id);
            delay(0);
            student = studentRepo.findById(id).get();
            studentCacheDomain.save(id, student);
            return student;
        } else {
            return student;
        }
    }

    public Student addStudent(Student student) {
        log.info("saving student {}", student);
        studentRepo.save(student);
        return studentCacheDomain.save(student.getId(), student);
    }

    public Student updateStudent(Student student) {
        log.info("updating student {}", student);
        studentRepo.save(student);
        return studentCacheDomain.save(student.getId(), student);
    }

    public void deleteStudent(String id) {
        log.info("deleting student with id {}", id);
        studentRepo.deleteById(id);
        studentCacheDomain.remove(id);
    }

    public void deleteAllStudents() {
        log.info("deleting all students");
        studentRepo.deleteAll();
        studentCacheDomain.clear();
    }
}
