package com.management.student.service;

import com.management.student.dto.*;
import com.management.student.exception.ResourceNotFoundException;
import com.management.student.model.Student;
import com.management.student.repository.StudentRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student;
    private StudentRequest studentRequest;

    @BeforeEach
    void setUp() {
        student = Student.builder()
                .id(1L)
                .firstName("first")
                .lastName("student")
                .email("first@example.com")
                .dateOfBirth(LocalDate.of(2007, 12, 9))
                .build();

        studentRequest = StudentRequest.builder()
                .firstName("first")
                .lastName("student")
                .email("first@example.com")
                .dateOfBirth(LocalDate.of(2007, 12, 9))
                .build();
    }
    @Test
    void createStudent() {
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        StudentResponse response = studentService.createStudent(studentRequest);
        assertEquals(student.getId(), response.getId());
    }
    @Test
    void getAllStudents() {
        when(studentRepository.findAll()).thenReturn(List.of(student));
        List<StudentResponse> response = studentService.getAllStudents();
        assertEquals(1, response.size());
    }
    @Test
    void getStudentById() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        StudentResponse response = studentService.getStudentById(1L);
        assertEquals(student.getId(), response.getId());
    }
    @Test
    void updateStudent() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        studentService.updateStudent(1L, studentRequest);
    }
    @Test
    void deleteStudent_Success() {
        when(studentRepository.existsById(1L)).thenReturn(true);
        doNothing().when(studentRepository).deleteById(1L);
        studentService.deleteStudent(1L);
        verify(studentRepository, times(1)).deleteById(1L);
    }
    @Test
    void deleteStudent_ThrowsWhenNotFound() {
        assertThrows(ResourceNotFoundException.class, () -> studentService.deleteStudent(1L));
    }
}