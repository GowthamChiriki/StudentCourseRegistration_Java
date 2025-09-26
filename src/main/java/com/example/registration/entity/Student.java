package com.example.registration.entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "enrollmentDate", nullable = false)
    private LocalDate enrollmentDate;

    //Mappings and Relationships
    //One-To-Many relationship with Registration
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Registration> registrations = new HashSet<>();

    //constructor
    public Student(){}

    public Student(String name, String email, LocalDate enrollmentDate){
        this.name = name;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
    }

    //Getters and Setters
    public long getId(){return id;}
    public void setId(Long id) { this.id = id; }

    public void setName(String name) {this.name = name;}
    public String getName() {return name;}

    public void setEmail(String email){this.email = email;}
    public String getEmail() {return email;}

    public void setEnrollmentDate(LocalDate enrollmentDate) {this.enrollmentDate = enrollmentDate;}
    public LocalDate getEnrollmentDate() {return enrollmentDate;}

    public void setRegistrations(Set<Registration> registrations){this.registrations = registrations;}
    public Set<Registration> getRegistrations() {return registrations;}


    @Override
    public String toString(){
        return String.format(
                "+----+-----------------+--------------------+----------------+\n" +
                        "| ID | Name            | Email              | Enrollment Date|\n" +
                        "+----+-----------------+--------------------+----------------+\n" +
                        "| %-2d | %-15s | %-18s | %-14s |\n" +
                        "+----+-----------------+--------------------+----------------+",
                id,
                name,
                email,
                enrollmentDate
        );
    }


}
