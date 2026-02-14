package com.mogorovskiy.hibcourses.domain.mapper;

import com.mogorovskiy.hibcourses.domain.dto.AuthorDto;
import com.mogorovskiy.hibcourses.domain.entities.AuthorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto toAuthorDto(AuthorEntity authorEntity);

    AuthorEntity toAuthorEntity(AuthorDto authorDto);

}
