package com.cosmos.teamcodingservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamCodingMemberInfoDto {

    private Integer teamMemberSeq; //팀멤버 seq
    private String userId; //유저 id
    private String userNickname; //유저 닉네임(가변)
    private int userGrant; //유저 권한(0 = 팀원, 1 = 팀장)
}
