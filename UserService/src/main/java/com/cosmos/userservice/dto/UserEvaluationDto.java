package com.cosmos.userservice.dto;

import com.cosmos.userservice.jpa.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEvaluationDto {
    private Integer userEvaluationSeq;
    private String toUserId;
    private Date createDate;
    private UserEntity user;
}
