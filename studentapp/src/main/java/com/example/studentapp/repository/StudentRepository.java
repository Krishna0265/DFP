package com.example.studentapp.repository;

import org.springframework.stereotype.Repository;
import com.example.studentapp.model.Student;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    private List<Student> students = new ArrayList<>();

    public void save(Student student) {
        students.add(student);
    }

    public List<Student> findAll() {
        return students;
    }
}