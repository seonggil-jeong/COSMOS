package com.cosmos.userservice.dto;

import com.cosmos.userservice.jpa.entity.UserLinkEntity;
import com.cosmos.userservice.jpa.entity.UserStackEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer userSeq;
    private String userId;
    private String userEmail;
    private String userPassword;
    private String userName;
    private String userNickname;
    private Timestamp userRegDate;
    private String userGender;
    private int userAge;
    private String userLocation;
    private int userRank;
    private int userStatus;

    private List<UserStackEntity> userStackEntityList;
    private List<UserLinkEntity> userLinkEntityList;

}
