package com.mogorovskiy.atomacademy.domain.entities;

import com.mogorovskiy.atomacademy.domain.CourseComplexityEnum;
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
@Table(name = "course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 100)
    private CourseComplexityEnum complexity;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private AuthorEntity author;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LessonEntity> lessons = new ArrayList<>();

    public CourseEntity(String title, String description, CourseComplexityEnum complexity) {
        this.title = title;
        this.description = description;
        this.complexity = complexity;
    }

    public void addLesson(LessonEntity lesson) {
        if (lessons == null) {
            this.lessons = new ArrayList<>();
        }
        this.lessons.add(lesson);
        lesson.setCourse(this);
    }

}

