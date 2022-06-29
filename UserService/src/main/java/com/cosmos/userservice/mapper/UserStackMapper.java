package com.cosmos.userservice.mapper;

import com.cosmos.userservice.dto.UserStackDto;
import com.cosmos.userservice.jpa.entity.UserStackEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserStackMapper {
    UserStackMapper INSTANCE = Mappers.getMapper(UserStackMapper.class);

    UserStackEntity userStackDtoToUserStackEntity(UserStackDto userStackDto);

    @Mapping(target = "user", ignore = true)
    UserStackDto userStackEntityToUserStackDto(UserStackEntity userStackEntity);
}