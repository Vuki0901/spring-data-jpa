package com.jpatutorial.springdatajpa.repository;

import com.jpatutorial.springdatajpa.entity.Course;
import com.jpatutorial.springdatajpa.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Page<Course> findByTitleContaining(String title, Pageable pageable);

    @Modifying
    @Transactional
    @Query(
            value = "update Course set teacher_id = ?1 where course_id = ?2",
            nativeQuery = true
    )
    void updateCourseTeacher(
            long tId,
            long cId
    );

}
