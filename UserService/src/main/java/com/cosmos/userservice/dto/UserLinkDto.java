package com.cosmos.userservice.dto;

import com.cosmos.userservice.jpa.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLinkDto {

    private Integer userLinkSeq;
    private String title;
    private String content;
    private UserEntity user;
}
