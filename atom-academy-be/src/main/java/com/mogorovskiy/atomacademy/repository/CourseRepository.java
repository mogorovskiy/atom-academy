package com.mogorovskiy.atomacademy.repository;

import com.mogorovskiy.atomacademy.domain.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

    @Query("select c from CourseEntity c left join fetch c.author")
    List<CourseEntity> findAllWithAuthors();

}
