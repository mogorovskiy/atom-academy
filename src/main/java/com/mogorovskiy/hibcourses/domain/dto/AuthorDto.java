package com.mogorovskiy.hibcourses.domain.dto;

import java.util.List;

public record AuthorDto(
        Long id,
        String name,
        String email,
        List<CourseDto> courses
) {
}
