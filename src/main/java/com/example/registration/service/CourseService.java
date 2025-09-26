package com.example.registration.service;

import com.example.registration.dao.CourseDAO;
import com.example.registration.entity.Course;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class CourseService {
    private final CourseDAO courseDAO = new CourseDAO();

    //Add course
    public void addCourse(Course course){
        courseDAO.save(course);
    }

    //update course
    public void updateCourse(Course course){
        if(courseDAO.findById(course.getId()) == null){
            throw new EntityNotFoundException("Course not found with ID: " + course.getId());
        }
        courseDAO.update(course);
    }

    //Delete course
    public void deleteCourse(Long courseId){
        Course course = courseDAO.findById(courseId);
        if( course == null){
            throw new EntityNotFoundException("Course not found with ID: " + courseId);
        }
        courseDAO.delete(course);
    }

    //Get course by id
    public Course getCourse(Long courseId){
        Course course = courseDAO.findById(courseId);
        if(course == null){
            throw new EntityNotFoundException("Course not found with ID: " + courseId);
        }
        return course;
    }

    //List all course
    public List<Course> getAllCourses(){
        return courseDAO.findAll();
    }
}
