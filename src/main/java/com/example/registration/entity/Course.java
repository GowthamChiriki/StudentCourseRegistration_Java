package com.example.registration.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "credit", nullable = false)
    private Integer credits;

    //mappings and relationships
    //One-To-Many relationship with Registration
    @OneToMany(mappedBy = "course_id", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Registration> registrations = new HashSet<>();


    //Constructors
    public Course(){}

    public Course(String title, String description, int credits){
        this.title = title;
        this.description = description;
        this.credits = credits;
    }

    //Getters and Setters
    public Long getId(){return id;}
    public void setId(Long id) {this.id = id;}

    public void setTitle(String title){this.title = title;}
    public String getTitle() {return title;}

    public void setDescription(String description) {this.description = description;}
    public String getDescription(){return description;}

    public void setCredits(int credits) {this.credits = credits;}
    public int getCredits(){return credits;}

    public void setRegistrations(Set<Registration> registrations) {this.registrations = registrations;}
    public Set<Registration> getRegistrations() {return registrations;}

    //to String
    @Override
    public String toString() {
        return String.format(
                "+----+----------------------+----------------------------+---------+\n" +
                        "| ID | Title                | Description                | Credits |\n" +
                        "+----+----------------------+----------------------------+---------+\n" +
                        "| %-2d | %-20s | %-26s | %-7d |\n" +
                        "+----+----------------------+----------------------------+---------+",
                id,
                title,
                description,
                credits
        );
    }


}
