package com.mogorovskiy.atomacademy.domain.mapper;

import com.mogorovskiy.atomacademy.domain.dto.LessonDto;
import com.mogorovskiy.atomacademy.domain.entities.LessonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    LessonDto toLessonDto(LessonEntity lessonEntity);

    LessonEntity toLessonEntity(LessonDto lessonDto);

}
