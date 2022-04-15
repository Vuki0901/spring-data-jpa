package com.jpatutorial.springdatajpa.repository;

import com.jpatutorial.springdatajpa.entity.Course;
import com.jpatutorial.springdatajpa.entity.Student;
import com.jpatutorial.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository repository;

    @Test
    public void printCourses() {
        List<Course> courses = repository.findAll();

        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithTeacher() {

        Teacher teacher = Teacher.builder()
                .firstName("Leo")
                .lastName("Vukoje")
                .build();

        Course course = Course.builder()
                .title("Python")
                .credit(5)
                .teacher(teacher)
                .build();

        repository.save(course);
    }

    @Test
    public void findAllPagination() {

        Pageable firstPageWithThreeRecords =
                PageRequest.of(0, 3);

        Pageable secondPageWithTwoRecords =
                PageRequest.of(1, 2);

        List<Course> courses =
                repository.findAll(secondPageWithTwoRecords).getContent();

        long totalElements = repository.findAll(secondPageWithTwoRecords).getTotalElements();
        int totalPages = repository.findAll(secondPageWithTwoRecords).getTotalPages();

        System.out.println("totalElements = " + totalElements);

        System.out.println("totalPages = " + totalPages);

        System.out.println("courses = " + courses);
    }

    @Test
    public void findAllWithSorting() {

        Pageable sortByTitle =
                PageRequest.of(0, 2, Sort.by("title"));

        Pageable sortByCreditDesc =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("credit")
                                .descending()
                );

        Pageable sortByTitleAndCreditDesc =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("title")
                                .descending()
                                .and(Sort.by("credit").descending())
                );

        List<Course> courses =
                repository.findAll(sortByTitle).getContent();

        System.out.println("courses = " + courses);

    }

    @Test
    public void printFindByTitleContaining(){
        Pageable firstPageTenRecords =
                PageRequest.of(0, 10);

        List<Course> courses = repository.findByTitleContaining("D", firstPageTenRecords).getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    public void updateCourseTeacher(){
        repository.updateCourseTeacher(2L,4L);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){

        Teacher teacher = Teacher.builder()
                .firstName("Kaka")
                .lastName("Maka")
                .build();

        Student student = Student.builder()
                .firstName("Skiko")
                .lastName("Mali")
                .emailId("skikoMali@gmail.com")
                .build();

        Course course = Course.builder()
                .title("AI")
                .credit(10)
                .teacher(teacher)
                .build();

        course.addStudents(student);

        repository.save(course);
    }
}