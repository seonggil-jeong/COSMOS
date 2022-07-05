package com.cosmos.userservice.mapper;

import com.cosmos.userservice.dto.UserEvaluationDto;
import com.cosmos.userservice.jpa.entity.UserEvaluationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserEvaluationMapper {
    UserEvaluationMapper INSTANCE = Mappers.getMapper(UserEvaluationMapper.class);

    UserEvaluationEntity userEvaluationDtoToUserEvaluationEntity(UserEvaluationDto userEvaluationDto);

    @Mapping(target = "user", ignore = true)
    UserEvaluationDto userEvaluationEntityToUserEvaluationDto(UserEvaluationEntity userEvaluationEntity);
}
