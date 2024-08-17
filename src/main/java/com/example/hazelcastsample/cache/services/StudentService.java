package com.example.hazelcastsample.cache.services;

import com.example.hazelcastsample.cache.domains.CacheDomain;
import com.example.hazelcastsample.cache.models.Student;
import com.example.hazelcastsample.cache.repos.StudentRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private CacheDomain<Student> studentCacheDomain;
    private StudentRepo studentRepo;

    public StudentService(@Qualifier("studentCacheDomain") CacheDomain<Student> studentCacheDomain, StudentRepo studentRepo) {
        this.studentCacheDomain = studentCacheDomain;
        this.studentRepo = studentRepo;
    }

    public Student getStudentById(String id) {
//        return studentCacheDomain.get(id);
        return studentRepo.findById(id).get();
    }

    public Student addStudent(Student student) {
//        return studentCacheDomain.save(student.getId(), student);
        return studentRepo.save(student);
    }

    public Student updateStudent(Student student) {
//        return studentCacheDomain.save(student.getId(), student);
        return studentRepo.save(student);
    }

    public void deleteStudent(String id) {
//        return studentCacheDomain.remove(id);
        studentRepo.deleteById(id);
    }

    public void deleteAllStudents() {
//        studentCacheDomain.clear();
        studentRepo.deleteAll();
    }
}
