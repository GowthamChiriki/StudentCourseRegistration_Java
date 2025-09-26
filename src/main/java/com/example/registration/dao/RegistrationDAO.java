package com.example.registration.dao;

import com.example.registration.entity.Course;
import com.example.registration.entity.Registration;
import com.example.registration.entity.Student;

import javax.imageio.spi.RegisterableService;
import java.util.List;

public class RegistrationDAO extends BaseDAO<Registration> {
    //To save registration
    public void save(Registration registration){
        executeVoid(session -> session.persist(registration));
    }

    //To delete the registration
    public void delete(Registration registration){
        executeVoid(session -> session.remove(registration));
    }

    //To find the registration by id
    public Registration findById(Long id){
        return execute(session -> session.get(Registration.class, id));
    }

    //Fetch all registrations
    public List<Registration> findAll(){
        return execute(session -> session.createQuery("FROM Registration ", Registration.class).list());
    }

    //Find by Student
    public List<Registration> findByStudent(Student student){
        return execute(session -> session.createQuery(
                "FROM Registration r WHERE r.student = :student", Registration.class)
                .setParameter("student", student)
                .list()
        );
    }

    //Find by Course
    public List<Registration> finByCourse(Course course){
        return execute(session -> session.createQuery(
                "FROM Registration r WHERE r.course = :course", Registration.class)
                .setParameter("course", course)
                .list()
        );
    }

    //Check duplicate registration
    public boolean exists(Student student, Course course){
        return execute(session -> !session.createQuery(
                "FROM Registration r WHERE r.student = :student AND r.course = :course", Registration.class)
                .setParameter("student", student)
                .setParameter("course", course)
                .list()
                .isEmpty()
        );
    }
}
