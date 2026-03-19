package com.mogorovskiy.atomacademy.domain.mapper;

import com.mogorovskiy.atomacademy.domain.dto.FullQuestionDto;
import com.mogorovskiy.atomacademy.domain.dto.ShortQuestionDto;
import com.mogorovskiy.atomacademy.domain.entities.QuestionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(source = "course.id", target = "courseId")
    FullQuestionDto toFullQuestionDto(QuestionEntity questionEntity);

    ShortQuestionDto toShortQuestionDto(QuestionEntity questionEntity);

    QuestionEntity toQuestionEntity(FullQuestionDto questionDto);

    QuestionEntity toQuestionEntity(ShortQuestionDto questionDto);

}
