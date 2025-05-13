package com.management.student.service;

import com.management.student.dto.*;
import com.management.student.exception.ResourceNotFoundException;
import com.management.student.model.Student;
import com.management.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentResponse createStudent(StudentRequest studentRequest) {
        if (studentRepository.existsByEmail(studentRequest.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        Student student = Student.builder()
                .firstName(studentRequest.getFirstName())
                .lastName(studentRequest.getLastName())
                .email(studentRequest.getEmail())
                .dateOfBirth(studentRequest.getDateOfBirth())
                .build();
        Student savedStudent = studentRepository.save(student);
        return mapToStudentResponse(savedStudent);
    }

    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToStudentResponse)
                .collect(Collectors.toList());
    }


    public StudentResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id " + id));
        return mapToStudentResponse(student);
    }

    public StudentResponse updateStudent(Long id, StudentRequest studentRequest) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id " + id));

        if (!student.getEmail().equals(studentRequest.getEmail())){
            if(studentRepository.existsByEmail(studentRequest.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }
        }
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setDateOfBirth(studentRequest.getDateOfBirth());

        Student updatedStudent = studentRepository.save(student);
        return mapToStudentResponse(updatedStudent);

    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("no student found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    private StudentResponse mapToStudentResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .dateOfBirth(student.getDateOfBirth())
                .build();
    }
}
