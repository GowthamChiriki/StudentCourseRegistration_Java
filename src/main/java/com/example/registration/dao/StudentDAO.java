package com.example.registration.dao;

import com.example.registration.entity.Student;

import java.util.List;

public class StudentDAO extends BaseDAO<Student>{
    //To save (persist) student
    public void save(Student student){
        executeVoid(session -> session.persist(student));
    }

    //To update the student
    public void update(Student student){
        executeVoid(session -> session.merge(student));
    }

    //To delete the student
    public void delete(Student student){
        executeVoid(session -> session.remove(session));
    }

    //To find the student by id
    public Student findById(Long id){
        return execute(session -> session.get(Student.class, id));
    }

    //To find the list of all student
    public List<Student> findAll(){
        return execute(session -> session.createQuery("FROM Student", Student.class).list());

    }
}
