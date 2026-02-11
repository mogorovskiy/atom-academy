package com.mogorovskiy.hibcourses.repository;

import com.mogorovskiy.hibcourses.domain.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
