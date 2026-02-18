package com.mogorovskiy.atomacademy.domain.mapper;

import com.mogorovskiy.atomacademy.domain.dto.AuthorDto;
import com.mogorovskiy.atomacademy.domain.entities.AuthorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto toAuthorDto(AuthorEntity authorEntity);

    AuthorEntity toAuthorEntity(AuthorDto authorDto);

}
