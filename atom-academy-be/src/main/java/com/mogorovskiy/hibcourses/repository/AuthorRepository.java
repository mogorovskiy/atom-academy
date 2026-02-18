package com.mogorovskiy.hibcourses.repository;

import com.mogorovskiy.hibcourses.domain.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
}
