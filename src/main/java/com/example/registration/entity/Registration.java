package com.example.registration.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "registrations", uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "course_id"}))
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    //Mappings and Relationships
    //Many-To-One Relationship with Student
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    //Many-To-One Relationship with Course
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    //constructors
    public Registration() {}

    public Registration(Student student, Course course, LocalDate registrationDate){
        this.student = student;
        this.course = course;
        this.registrationDate = registrationDate;
    }

    //Getters and Setters
    public void setStudent(Student student){this.student = student;}
    public Student getStudent() {return student;}

    public void setCourse(Course course) {this.course = course;}
    public Course getCourse() {return course;}

    public void setRegistrationDate(LocalDate registrationDate) {this.registrationDate = registrationDate;}
    public LocalDate getRegistrationDate(){return registrationDate;}

    @Override
    public String toString() {
        return String.format(
                "+----+------------+-----------+----------------+\n" +
                        "| ID | Student ID | Course ID | Registration Date |\n" +
                        "+----+------------+-----------+----------------+\n" +
                        "| %-2d | %-10d | %-9d | %-14s |\n" +
                        "+----+------------+-----------+----------------+",
                id,
                student.getId(),
                course.getId(),
                registrationDate
        );
    }



}
