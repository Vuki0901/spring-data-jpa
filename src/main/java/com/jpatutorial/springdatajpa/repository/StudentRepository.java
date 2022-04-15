package com.jpatutorial.springdatajpa.repository;

import com.jpatutorial.springdatajpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String name);

    List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String guardianName);

    //JPQL
    @Query("select s.firstName from Student s where s.emailId = ?1")
    String getStudentByEmailAddress(String emailId);


    @Modifying
    @Transactional
    @Query(
            value = "update tbl_student set first_name = ?1 where email_address = ?2",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(String firstName, String emailId);

    //UPDATE all student attributes
    @Modifying
    @Transactional
    @Query(value = "update tbl_student set first_name = ?1, last_name = ?2, email_address = ?3, guardian_name = ?4, guardian_email = ?5, guardian_mobile = ?6 where student_id = ?7 ", nativeQuery = true)
    void updateStudent(
            String firstName,
            String lastName,
            String emailId,
            String guardianName,
            String guardianEmail,
            String guardianMobile,
            Long studentId
    );
}
