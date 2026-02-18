package com.mogorovskiy.hibcourses.repository;

import com.mogorovskiy.hibcourses.domain.entities.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<LessonEntity, Long> {
}
