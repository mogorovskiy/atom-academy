package com.mogorovskiy.atomacademy.domain.mapper;

import com.mogorovskiy.atomacademy.domain.dto.UserDto;
import com.mogorovskiy.atomacademy.domain.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(UserEntity userEntity);

    UserEntity toUserEntity(UserDto userDto);

}
