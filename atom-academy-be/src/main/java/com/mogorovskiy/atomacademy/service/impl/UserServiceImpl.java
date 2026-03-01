package com.mogorovskiy.atomacademy.service.impl;

import com.mogorovskiy.atomacademy.api.request.create.UserCreateAndUpdateRequest;
import com.mogorovskiy.atomacademy.domain.entities.UserEntity;
import com.mogorovskiy.atomacademy.repository.UserRepository;
import com.mogorovskiy.atomacademy.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository UserRepository;

    @Transactional
    @Override
    public UserEntity createUser(UserCreateAndUpdateRequest userCreateRequest) {
        log.info("Creating user in DB: {}", userCreateRequest.name());
        UserEntity entity = UserEntity.builder()
                .name(userCreateRequest.name())
                .email(userCreateRequest.email())
                .password(userCreateRequest.password())
                .role(userCreateRequest.role())
                .build();

        return UserRepository.save(entity);
    }

    @Override
    public UserEntity updateUser(Long id, UserCreateAndUpdateRequest userCreateRequest) {
        UserEntity entity = UserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        entity.setName(userCreateRequest.name());
        entity.setEmail(userCreateRequest.email());

        return UserRepository.save(entity);
    }

    @Override
    public UserEntity patchUser(Long id, UserCreateAndUpdateRequest userCreateRequest) {
        UserEntity entity = UserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        boolean changed = false;
        if (userCreateRequest.name() != null) {
            changed |= !userCreateRequest.name().equals(entity.getName());
            entity.setName(userCreateRequest.name());
        }
        if (userCreateRequest.email() != null) {
            changed |= !userCreateRequest.email().equals(entity.getEmail());
            entity.setEmail(userCreateRequest.email());
        }

        if (changed) {
            return UserRepository.save(entity);
        }

        return entity;
    }

    @Override
    public UserEntity getUser(Long id) {
        return UserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return UserRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        log.info("Deleting user by id: {}", id);
        UserRepository.deleteById(id);
    }
}
