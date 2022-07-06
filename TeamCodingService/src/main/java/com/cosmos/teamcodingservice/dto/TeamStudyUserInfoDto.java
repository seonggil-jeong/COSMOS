package com.cosmos.teamcodingservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamStudyUserInfoDto {

    private Integer teamStudyUserInfoSeq; //teamStudyUser seq
    private Date teamStudyDate; //스터디 모임 일자
    private String userId; //유저 id
    private int userParticipation; //0 = 불참, 1 = 참가
}
