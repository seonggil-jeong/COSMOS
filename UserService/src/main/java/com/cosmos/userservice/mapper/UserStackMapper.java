package com.cosmos.userservice.mapper;

import com.cosmos.userservice.dto.UserDto;
import com.cosmos.userservice.dto.UserStackDto;
import com.cosmos.userservice.jpa.entity.UserStackEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserStackMapper {
    UserStackMapper INSTANCE = Mappers.getMapper(UserStackMapper.class);

    UserStackEntity userStackDtoToUserStackEntity(UserStackDto userStackDto);

    @Mapping(target = "user", ignore = true) // Entity 를 Dto 로 변환 시 user (UserEntity) 는 포함 X
    UserStackDto userStackEntityToUserStackDto(UserStackEntity userStackEntity);
}
