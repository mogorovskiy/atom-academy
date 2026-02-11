package com.mogorovskiy.hibcourses.service;

import com.mogorovskiy.hibcourses.domain.entities.CourseEntity;

import java.util.List;

public interface CourseService {

    CourseEntity createCourse(CourseEntity author);

    CourseEntity updateCourse(CourseEntity author);

    CourseEntity getCourse(Long id);

    List<CourseEntity> getAllCourses();

    void deleteCourse(Long id);
}
