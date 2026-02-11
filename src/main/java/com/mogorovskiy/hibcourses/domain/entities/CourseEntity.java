package com.mogorovskiy.hibcourses.domain.entities;

import com.mogorovskiy.hibcourses.domain.CourseComplexityEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 100)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 100)
    private CourseComplexityEnum complexity;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private AuthorEntity author;

    public CourseEntity(String title, String description, CourseComplexityEnum complexity) {
        this.title = title;
        this.description = description;
        this.complexity = complexity;
    }

}
