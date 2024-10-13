package com.example.hazelcastsample.cache.embedded.services;

import com.example.hazelcastsample.cache.embedded.domains.HzCacheDomain;
import com.example.hazelcastsample.commons.models.Student;
import com.example.hazelcastsample.cache.embedded.repos.StudentRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.hazelcastsample.commons.utils.Util.delay;

@Service
@Slf4j
// cache and persistence is maintained by developer.
public class HzStudentGenericCacheService {

    private final HzCacheDomain<Student> studentCacheDomain;
    private final StudentRepo studentRepo;

    public HzStudentGenericCacheService(@Qualifier("HzEmbeddedStudentCacheDomain") HzCacheDomain<Student> studentCacheDomain, StudentRepo studentRepo) {
        this.studentCacheDomain = studentCacheDomain;
        this.studentRepo = studentRepo;
    }

    public Student getStudentById(String id) {
        log.info("retrieving student with id {}", id);
        Student student = studentCacheDomain.get(id);
        if(student == null) {
            log.info("student with id {} not found in the cache", id);
            delay(0);
            student = Optional
                    .of(studentRepo.findById(id))
                    .get()
                    .orElseThrow(() -> new NullPointerException("student with id " + id + " not found"));
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
