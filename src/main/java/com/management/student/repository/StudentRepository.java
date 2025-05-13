package com.management.student.repository;

import com.management.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Boolean existsByEmail(String email);

}
