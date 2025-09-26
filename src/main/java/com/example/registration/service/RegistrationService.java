package com.example.registration.service;

import com.example.registration.dao.RegistrationDAO;
import com.example.registration.entity.Course;
import com.example.registration.entity.Registration;
import com.example.registration.entity.Student;
import com.example.registration.exception.DuplicateRegistrationException;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.util.List;

public class RegistrationService {
    private final RegistrationDAO registrationDAO = new RegistrationDAO();
    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();

    //Register a student for a course
    public void registerStudentToCourse(Long studentId, Long courseId){
        Student student = studentService.getStudent(studentId);
        Course course = courseService.getCourse(courseId);

        //Check duplicate registration
        if(registrationDAO.exists(student, course)){
            throw new DuplicateRegistrationException("Student " + studentId + " is already registered for course " + courseId);
        }

        Registration registration = new Registration(student, course, LocalDate.now());
        registrationDAO.save(registration);
    }

    //List registrations by a student
    public List<Registration> getRegistrationsByStudent(Long studentId){
        Student student = studentService.getStudent(studentId);
        return registrationDAO.findByStudent(student);
    }

    //List registrations by course
    public List<Registration> getRegistrationsByCourse(Long courseId){
        Course course = courseService.getCourse(courseId);
        return registrationDAO.finByCourse(course);
    }

    //Delete registration
    public void deleteRegistration(Long registrationId){
        Registration registration = registrationDAO.findById(registrationId);
        if(registration == null){
            throw new EntityNotFoundException("registration not found with ID: " + registrationId);
        }
        registrationDAO.delete(registration);
    }

    //List all registrations
    public List<Registration> getAllRegistrations(){
        return registrationDAO.findAll();
    }

}
