package com.cosmos.userservice.dto;


import com.cosmos.userservice.jpa.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStackDto {
    private Integer userStackSeq;
    private String userStack;
    private UserEntity user;
}
