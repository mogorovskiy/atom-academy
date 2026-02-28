package com.mogorovskiy.atomacademy.api.controller;

import com.mogorovskiy.atomacademy.api.request.create.UserCreateAndUpdateRequest;
import com.mogorovskiy.atomacademy.domain.dto.UserDto;
import com.mogorovskiy.atomacademy.domain.entities.UserEntity;
import com.mogorovskiy.atomacademy.domain.mapper.UserMapper;
import com.mogorovskiy.atomacademy.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserCreateAndUpdateRequest createRequest) {
        log.info("Creating user: {}", createRequest);

        UserEntity entity = userService.createUser(createRequest);
        UserDto userDto = userMapper.toUserDto(entity);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        log.info("Getting user by id: {}", id);

        UserEntity entity = userService.getUser(id);
        UserDto userDto = userMapper.toUserDto(entity);

        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserCreateAndUpdateRequest updateRequest
    ) {
        log.info("Updating user id {}: {}", id, updateRequest);
        UserEntity entity = userService.updateUser(id, updateRequest);
        return ResponseEntity.ok(userMapper.toUserDto(entity));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> patchUser(
            @PathVariable Long id,
            @Valid @RequestBody UserCreateAndUpdateRequest updateRequest) {
        log.info("Patching user id {}: {}", id, updateRequest);
        UserEntity entity = userService.patchUser(id, updateRequest);
        return ResponseEntity.ok(userMapper.toUserDto(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
        log.info("Deleting user by id: {}", id);

        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
