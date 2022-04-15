package com.jpatutorial.springdatajpa.repository;

import com.jpatutorial.springdatajpa.entity.Guardian;
import com.jpatutorial.springdatajpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("shabbir@gmail.com")
                .firstName("Shabbir")
                .lastName("Dawoodi")
                //.guardianName("Nikhil")
                //.guardianEmail("nikhil@gmail.com")
                //.guardianMobile("99999999")
                .build();

        studentRepository.save(student);
    }
    @Test
    public void saveStudentWithGuardian(){

        Guardian guardian = Guardian.builder()
                .name("Nikhil")
                .email("nikhil@gmail.com")
                .mobile("99999999")
                .build();

        Student student = Student.builder()
                .firstName("Shivam")
                .emailId("shiva@gmail.com")
                .lastName("Kumar")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudent(){
        List<Student> studentList =
                studentRepository.findAll();

        System.out.println(studentList);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> students =
                studentRepository.findByFirstName("Shivam");

        System.out.println(students);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students =
                studentRepository.findByFirstNameContaining("Sh");

        System.out.println(students);
    }
    @Test
    public void printStudentByLastNameNotNull(){
        List<Student> students =
                studentRepository.findByLastNameNotNull();

        System.out.println(students);
    }

    @Test
    public void printStudentByGuardianName(){
        List<Student> students =
                studentRepository.findByGuardianName("Nikhil");

        System.out.println(students);
    }
    @Test
    public void printGetStudentByEmailAddress(){
        String studentName = studentRepository
                .getStudentByEmailAddress("shabbir@gmail.com");

        System.out.println("Student name : " + studentName);
    }

    @Test
    public void updateStudentNameByEmailId(){
        studentRepository.updateStudentNameByEmailId(
                "shabbir dawoodi",
                "shabbir@gmail.com"
        );
    }
}