package com.example.registration.service;

import com.example.registration.dao.StudentDAO;
import com.example.registration.entity.Student;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class StudentService {
    private final StudentDAO studentDAO = new StudentDAO();

    //Add student
    public void addStudent(Student student){
        studentDAO.save(student);
    }

    //Update student
    public void updateStudent(Student student){
        if(studentDAO.findById(student.getId()) == null){
            throw new EntityNotFoundException("Student not found with ID: " + student.getId());
        }
        studentDAO.update(student);
    }

    //Delete student
    public void deleteStudent(Long studentId){
        Student student = studentDAO.findById(studentId);
        if(student == null){
            throw new EntityNotFoundException("Student not found with ID: " + studentId);
        }
        studentDAO.delete(student);

    }

    //Get student by id
    public Student getStudent(Long studentId){
        Student student = studentDAO.findById(studentId);
        if(student == null){
            throw new EntityNotFoundException("Student not found with Id: " + studentId);
        }
        return student;
    }

    //List all student
    public List<Student> getAllStudents(){
        return studentDAO.findAll();
    }

}
