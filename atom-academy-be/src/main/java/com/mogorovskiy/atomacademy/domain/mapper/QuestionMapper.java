package com.mogorovskiy.atomacademy.domain.mapper;

import com.mogorovskiy.atomacademy.domain.dto.QuestionDto;
import com.mogorovskiy.atomacademy.domain.entities.QuestionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionDto toQuestionDto(QuestionEntity questionEntity);

    QuestionEntity toQuestionEntity(QuestionDto questionDto);

}
