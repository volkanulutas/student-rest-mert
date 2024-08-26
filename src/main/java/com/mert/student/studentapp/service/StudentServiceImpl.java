package com.mert.student.studentapp.service;

import com.mert.student.studentapp.exception.StudentAlreadyExistException;
import com.mert.student.studentapp.exception.StudentNotFoundException;
import com.mert.student.studentapp.model.Student;
import com.mert.student.studentapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //@Component @Configuration
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository; // StudentRepository studentRepository = new StudentRepository();

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student createStudent(Student studentParam) throws StudentAlreadyExistException {
        Student student = findStudent(studentParam);
        if (student != null) {
            throw new StudentAlreadyExistException("Error is occurred while creating student.");
        }
        return studentRepository.save(studentParam);
    }

    @Override
    public Student updateStudent(Student studentParam) throws StudentNotFoundException {
        Student student = findStudent(studentParam);
        if (student == null) {
            throw new StudentNotFoundException("Error is occurred while updating student.");
        }
        return studentRepository.save(studentParam);
    }

    @Override
    public void deleteStudent(long id) throws StudentNotFoundException {
        Student student = findStudent(id);
        if (student == null) {
            throw new StudentNotFoundException("Error is occurred while deleting student.");
        }
        this.studentRepository.deleteById(id);
    }

    @Override
    public Student getStudentById(long id) throws StudentNotFoundException {
        Student student = findStudent(id);
        if (student == null) {
            throw new StudentNotFoundException("Error is occurred while getting student by id.");
        }
        return student;
    }

    private Student findStudent(Student studentParam) {
        Optional<Student> optionalStudent = this.studentRepository.findById(studentParam.getId());
        boolean present = optionalStudent.isPresent();
        if (present) {
            return optionalStudent.get();
        }
        return null;
    }

    private Student findStudent(Long id) {
        Optional<Student> optionalStudent = this.studentRepository.findById(id);
        boolean present = optionalStudent.isPresent();
        if (present) {
            return optionalStudent.get();
        }
        return null;
    }
}
