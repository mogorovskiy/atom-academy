package com.mogorovskiy.hibcourses.domain.mapper;

import com.mogorovskiy.hibcourses.domain.dto.CourseDto;
import com.mogorovskiy.hibcourses.domain.entities.CourseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDto toCourseDto(CourseEntity courseEntity);

    CourseEntity toCourseEntity(CourseDto courseDto);

}
