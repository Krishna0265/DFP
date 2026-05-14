package com.example.studentapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.studentapp.model.Student;
import com.example.studentapp.service.StudentService;
import com.example.studentapp.component.UtilityComponent;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @Autowired
    private UtilityComponent utility;

    @Autowired
    private String appName; // Injected from @Bean

    @PostMapping
    public String addStudent(@RequestBody Student student) {
        service.addStudent(student);
        return utility.formatMessage("Student added successfully in " + appName);
    }

    @GetMapping
    public List<Student> getStudents() {
        return service.getStudents();
    }
}