package com.mogorovskiy.atomacademy.repository;

import com.mogorovskiy.atomacademy.domain.entities.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonRepository extends JpaRepository<LessonEntity, Long> {

    @Query("select l from LessonEntity l left join fetch l.course")
    List<LessonEntity> findLessonsByCourseId(Long courseId);
}
