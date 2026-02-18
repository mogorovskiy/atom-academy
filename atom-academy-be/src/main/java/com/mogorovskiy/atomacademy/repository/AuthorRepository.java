package com.mogorovskiy.atomacademy.repository;

import com.mogorovskiy.atomacademy.domain.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
}
