package com.mogorovskiy.atomacademy.repository;

import com.mogorovskiy.atomacademy.domain.entities.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<LessonEntity, Long> {
}
