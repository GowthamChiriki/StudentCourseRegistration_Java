package com.example.registration.dao;

import com.example.registration.entity.Course;
import com.example.registration.entity.Registration;
import com.example.registration.entity.Student;

import java.util.List;

public class RegistrationDAO extends BaseDAO<Registration> {

    // Save registration
    public void save(Registration registration) {
        executeVoid(session -> session.persist(registration));
    }

    // Delete registration
    public void delete(Registration registration) {
        executeVoid(session -> session.remove(registration));
    }

    // Find registration by ID
    public Registration findById(Long id) {
        return execute(session -> session.get(Registration.class, id));
    }

    // Fetch all registrations with student and course initialized
    public List<Registration> findAll() {
        return execute(session -> session.createQuery(
                        "SELECT r FROM Registration r JOIN FETCH r.student JOIN FETCH r.course", Registration.class)
                .list());
    }

    // Find registrations by student (with student and course fetched)
    public List<Registration> findByStudent(Student student) {
        return execute(session -> session.createQuery(
                        "SELECT r FROM Registration r JOIN FETCH r.student JOIN FETCH r.course WHERE r.student = :student", Registration.class)
                .setParameter("student", student)
                .getResultList());
    }

    // Find registrations by course (with student and course fetched)
    public List<Registration> findByCourse(Course course) {
        return execute(session -> session.createQuery(
                        "SELECT r FROM Registration r JOIN FETCH r.student JOIN FETCH r.course WHERE r.course = :course", Registration.class)
                .setParameter("course", course)
                .getResultList());
    }

    // Check duplicate registration for same student-course pair
    public boolean exists(Student student, Course course) {
        return execute(session -> !session.createQuery(
                        "FROM Registration r WHERE r.student = :student AND r.course = :course", Registration.class)
                .setParameter("student", student)
                .setParameter("course", course)
                .list()
                .isEmpty());
    }
}
