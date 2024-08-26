package com.mert.student.studentapp.service;

import com.mert.student.studentapp.exception.StudentAlreadyExistException;
import com.mert.student.studentapp.exception.StudentNotFoundException;
import com.mert.student.studentapp.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    Student createStudent(Student student) throws StudentAlreadyExistException;

    Student updateStudent(Student student) throws StudentNotFoundException;

    void deleteStudent(long id) throws StudentNotFoundException;

    Student getStudentById(long id) throws StudentNotFoundException;
}
