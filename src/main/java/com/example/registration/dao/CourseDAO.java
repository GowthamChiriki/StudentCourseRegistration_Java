package com.example.registration.dao;

import com.example.registration.entity.Course;

import java.util.List;

public class CourseDAO extends BaseDAO<Course>{
    // To save the course
    public void save(Course course){
        executeVoid(session -> session.persist(course));
    }

    //To update the course
    public void update(Course course){
        executeVoid(session -> session.merge(course));
    }

    //To delete the course
    public void delete(Course course){
        executeVoid(session -> session.remove(course));
    }

    //To find a course by id
    public Course findById(Long id){
        return execute(session -> session.get(Course.class, id));
    }

    //To find list of all courses
    public List<Course> findAll(){
        return execute(session -> session.createQuery("FROM Course", Course.class).list());
    }

}
