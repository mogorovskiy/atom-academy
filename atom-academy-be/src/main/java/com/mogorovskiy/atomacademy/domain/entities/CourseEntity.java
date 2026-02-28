package com.mogorovskiy.atomacademy.domain.entities;

import com.mogorovskiy.atomacademy.domain.CourseComplexitiesEnum;
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
    private CourseComplexitiesEnum complexity;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private UserEntity creator;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionEntity> questions = new ArrayList<>();

    public CourseEntity(String title, String description, CourseComplexitiesEnum complexity) {
        this.title = title;
        this.description = description;
        this.complexity = complexity;
    }

    public void addQuestions(QuestionEntity question) {
        if (questions == null) {
            this.questions = new ArrayList<>();
        }
        this.questions.add(question);
        question.setCourse(this);
    }

}

