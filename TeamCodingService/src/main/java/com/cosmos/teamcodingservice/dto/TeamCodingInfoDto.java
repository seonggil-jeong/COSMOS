package com.cosmos.teamcodingservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamCodingInfoDto {

    private Integer teamSeq;
    private String teamTitle; //스터디 모임 title
    private String teamContext; //스터디 모임 설명
    private Date teamStartDT; //스터디 시작일
    private Date teamEndDT; //스터디 종료일
    private int teamTotalCount; //스터디 현재 인원
    private int teamMaxCount; //스터디 참가 가능 최대 인원
    private String teamChatLink; //스터디 오픈채팅방 링크
}
