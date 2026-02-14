package com.mogorovskiy.hibcourses.service;

import com.mogorovskiy.hibcourses.api.CourseCreateRequest;
import com.mogorovskiy.hibcourses.domain.entities.CourseEntity;

import java.util.List;

public interface CourseService {

    CourseEntity createCourse(CourseCreateRequest course);

    CourseEntity updateCourse(CourseCreateRequest course);

    CourseEntity getCourse(Long id);

    List<CourseEntity> getAllCourses();

    void deleteCourse(Long id);
}
