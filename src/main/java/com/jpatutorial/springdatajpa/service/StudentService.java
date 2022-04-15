package com.jpatutorial.springdatajpa.service;

import com.jpatutorial.springdatajpa.entity.Guardian;
import com.jpatutorial.springdatajpa.entity.Student;
import com.jpatutorial.springdatajpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        Optional<Student> s = studentRepository.findById(id);
        if (s == null){
            throw new IllegalStateException("Student with id: " + id + " doesn't exist");
        }
        return s;
    }

    public void addNewStudent(Student s, Guardian g){
        s.setGuardian(g);
        studentRepository.save(s);
    }

    public void deleteStudent(long studentId) {
        studentRepository.deleteById(studentId);
    }

    public void updateStudent(Long id, Student updatedStudent) {
        Optional<Student> s = studentRepository.findById(id);
        if(s != null){
            studentRepository.updateStudent(
                    updatedStudent.getFirstName(),
                    updatedStudent.getLastName(),
                    updatedStudent.getEmailId(),
                    updatedStudent.getGuardian().getName(),
                    updatedStudent.getGuardian().getEmail(),
                    updatedStudent.getGuardian().getMobile(),
                    id
            );
        } else {
            throw new IllegalStateException("Such student doesn't exist");
        }


    }
}
