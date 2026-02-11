package com.mogorovskiy.hibcourses.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "author")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String email;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseEntity> courses = new ArrayList<>();

    public AuthorEntity(String name, String email, List<CourseEntity> courses) {
        this.name = name;
        this.email = email;
        setCourses(courses);
    }

    public AuthorEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void addCourse(CourseEntity courses) {
        if (courses == null) {
            this.courses = new ArrayList<>();
        }
        this.courses.add(courses);
        courses.setAuthor(this);
    }
}
