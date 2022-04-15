package com.jpatutorial.springdatajpa.request_model;

import com.jpatutorial.springdatajpa.entity.Guardian;
import com.jpatutorial.springdatajpa.entity.Student;

public class StudentCreationRequest {

    public StudentCreationRequest(Student student, Guardian guardian) {
        this.student = student;
        this.guardian = guardian;
    }

    private Student student;
    private Guardian guardian;

    public Student getStudent() {
        return student;
    }

    public Guardian getGuardian() {
        return guardian;
    }


}
