package com.mogorovskiy.atomacademy.domain.dto;

import com.mogorovskiy.atomacademy.domain.UserRolesEnum;

import java.util.List;

public record UserDto(
        Long id,
        String name,
        String email,
        String password,
        UserRolesEnum role,
        List<CourseDto> courses
) {
}
