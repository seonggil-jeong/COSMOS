package com.cosmos.userservice.dto;

import com.cosmos.userservice.jpa.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEvaluationDto {
    private Integer userEvaluationSeq;
    private String toUserId;
    private UserEntity user;
}
