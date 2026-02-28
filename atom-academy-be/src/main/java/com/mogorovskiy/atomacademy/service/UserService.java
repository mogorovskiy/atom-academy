package com.mogorovskiy.atomacademy.service;

import com.mogorovskiy.atomacademy.api.request.create.UserCreateAndUpdateRequest;
import com.mogorovskiy.atomacademy.domain.entities.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity createUser(UserCreateAndUpdateRequest userRequest);

    UserEntity updateUser(Long id, UserCreateAndUpdateRequest userRequest);

    UserEntity patchUser(Long id, UserCreateAndUpdateRequest userRequest);

    UserEntity getUser(Long id);

    List<UserEntity> getAllUsers();

    void deleteUser(Long id);
}
