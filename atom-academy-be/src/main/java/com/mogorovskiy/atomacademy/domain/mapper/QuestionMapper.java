package com.mogorovskiy.atomacademy.domain.mapper;

import com.mogorovskiy.atomacademy.domain.dto.QuestionDto;
import com.mogorovskiy.atomacademy.domain.entities.QuestionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(source = "course.id", target = "courseId")
    QuestionDto toQuestionDto(QuestionEntity questionEntity);

    QuestionEntity toQuestionEntity(QuestionDto questionDto);

}
