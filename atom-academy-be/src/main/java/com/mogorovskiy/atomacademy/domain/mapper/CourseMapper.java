package com.mogorovskiy.atomacademy.domain.mapper;

import com.mogorovskiy.atomacademy.domain.dto.CourseDto;
import com.mogorovskiy.atomacademy.domain.entities.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(source = "creator.name", target = "creatorName")
    @Mapping(source = "creator.id", target = "creatorId")
    CourseDto toCourseDto(CourseEntity courseEntity);

    CourseEntity toCourseEntity(CourseDto courseDto);

}
