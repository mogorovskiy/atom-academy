package com.mogorovskiy.atomacademy.domain.entities;

import com.mogorovskiy.atomacademy.domain.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String password;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseEntity> courses = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public UserEntity(String name, String email, List<CourseEntity> courses) {
        this.name = name;
        this.email = email;
        setCourses(courses);
    }

    public UserEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void addCourse(CourseEntity courses) {
        if (courses == null) {
            this.courses = new ArrayList<>();
        }
        this.courses.add(courses);
        courses.setCreator(this);
    }
}
