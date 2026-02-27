package com.mogorovskiy.atomacademy.service;

import com.mogorovskiy.atomacademy.api.request.create.CourseCreateRequest;
import com.mogorovskiy.atomacademy.api.request.update.CourseUpdateRequest;
import com.mogorovskiy.atomacademy.domain.dto.CourseDto;
import com.mogorovskiy.atomacademy.domain.entities.CourseEntity;

import java.util.List;

public interface CourseService {

    CourseEntity createCourse(CourseCreateRequest createRequest);

    CourseEntity updateCourse(Long id, CourseUpdateRequest updateRequest);

    CourseEntity patchCourse(Long id, CourseUpdateRequest updateRequest);

    CourseDto getCourse(Long id);

    CourseEntity getCourseEntity(Long id);

    List<CourseDto> getAllCourses();

    void deleteCourse(Long id);
}
