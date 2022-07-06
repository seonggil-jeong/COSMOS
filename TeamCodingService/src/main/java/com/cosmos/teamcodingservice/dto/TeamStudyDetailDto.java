package com.cosmos.teamcodingservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamStudyDetailDto {

    private Integer teamStudyDetailSeq; //teamStudyDetail seq
    private String userId; //유저 id
    private String teamStudyDetailContents; //유저별 특정 날짜 스터디 후기
}
