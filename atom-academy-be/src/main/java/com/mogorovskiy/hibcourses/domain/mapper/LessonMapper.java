package com.mogorovskiy.hibcourses.domain.mapper;

import com.mogorovskiy.hibcourses.domain.dto.LessonDto;
import com.mogorovskiy.hibcourses.domain.entities.LessonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    LessonDto toLessonDto(LessonEntity lessonEntity);

    LessonEntity toLessonEntity(LessonDto lessonDto);

}
