package com.mogorovskiy.atomacademy.repository;

import com.mogorovskiy.atomacademy.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
