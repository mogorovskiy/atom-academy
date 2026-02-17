package com.mogorovskiy.hibcourses.domain.mapper;

import com.mogorovskiy.hibcourses.domain.dto.CourseDto;
import com.mogorovskiy.hibcourses.domain.entities.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(source = "author.id", target = "authorId")
    CourseDto toCourseDto(CourseEntity courseEntity);

    CourseEntity toCourseEntity(CourseDto courseDto);

}
