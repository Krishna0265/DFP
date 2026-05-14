package com.example.studentapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentapp.model.Student;
import com.example.studentapp.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public void addStudent(Student student) {
        repository.save(student);
    }

    public List<Student> getStudents() {
        return repository.findAll();
    }
}