package com.cosmos.userservice.mapper;

import com.cosmos.userservice.dto.UserLinkDto;
import com.cosmos.userservice.jpa.entity.UserLinkEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserLinkMapper {
    UserLinkMapper INSTANCE = Mappers.getMapper(UserLinkMapper.class);

    UserLinkEntity userDtoToUserLinkEntity(UserLinkDto userLinkDto);

    @Mapping(target = "user", ignore = true)
    UserLinkDto userLinkEntityToUserLinkDto(UserLinkEntity userLinkEntity);
}