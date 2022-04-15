package com.jpatutorial.springdatajpa.controller;

import com.jpatutorial.springdatajpa.entity.Guardian;
import com.jpatutorial.springdatajpa.entity.Student;
import com.jpatutorial.springdatajpa.request_model.StudentCreationRequest;
import com.jpatutorial.springdatajpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //READ all
    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    //READ by ID
    @RequestMapping(path = "/{studentId}", method = RequestMethod.GET)
    public Optional<Student> getStudentById(
            @PathVariable(name = "studentId") Long id
    ){
        return studentService.getStudentById(id);
    }

    //CREATE
    @PostMapping
    public void addStudent(
            @RequestBody StudentCreationRequest studentCreationRequest
    ){
        Student student = studentCreationRequest.getStudent();
        Guardian guardian = studentCreationRequest.getGuardian();
        studentService.addNewStudent(student, guardian);
    }

    //UPDATE
    @RequestMapping(path = "/{studentId}", method = RequestMethod.PUT)
    public void updateStudent(
            @PathVariable(name = "studentId") Long id,
            @RequestBody Student updatedStudent
    ){
        studentService.updateStudent(id, updatedStudent);
    }

    //DELETE
    @DeleteMapping(path = "/{studentId}")
    public void deleteStudent(
            @PathVariable(name = "studentId") long studentId
    ){
        studentService.deleteStudent(studentId);
    }
}
