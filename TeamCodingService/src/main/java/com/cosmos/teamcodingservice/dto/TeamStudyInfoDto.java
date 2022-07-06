package com.cosmos.teamcodingservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamStudyInfoDto {

    private Integer teamStudySeq;
    private Date teamStudyDate; //스터디 모임 일자
    private String teamStudyContents; //특정 일자 스터디 설명
}
