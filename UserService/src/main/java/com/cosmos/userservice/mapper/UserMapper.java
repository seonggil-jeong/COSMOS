package com.cosmos.userservice.mapper;

import com.cosmos.userservice.dto.UserDto;
import com.cosmos.userservice.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // UserDto --> UserEntity
    UserEntity userDtoToUserEntity(UserDto userDto);

    // UserEntity --> UserDto
    UserDto userEntityToUserDto(UserEntity userEntity);
}