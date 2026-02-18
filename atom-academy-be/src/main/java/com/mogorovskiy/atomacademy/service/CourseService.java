package com.mogorovskiy.atomacademy.service;

import com.mogorovskiy.atomacademy.api.request.create.CourseCreateRequest;
import com.mogorovskiy.atomacademy.api.request.update.CourseUpdateRequest;
import com.mogorovskiy.atomacademy.domain.entities.CourseEntity;

import java.util.List;

public interface CourseService {

    CourseEntity createCourse(CourseCreateRequest createRequest);

    CourseEntity updateCourse(Long id, CourseUpdateRequest updateRequest);

    CourseEntity patchCourse(Long id, CourseUpdateRequest updateRequest);

    CourseEntity getCourse(Long id);

    List<CourseEntity> getAllCourses();

    void deleteCourse(Long id);
}
