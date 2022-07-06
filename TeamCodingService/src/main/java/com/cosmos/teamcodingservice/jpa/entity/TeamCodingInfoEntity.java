package com.cosmos.teamcodingservice.jpa.entity;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter //Setter는 쓰지않는다. 값 타입은 될 수 있으면 불변 객체로 설계해야 하기때문에.
@Entity
@Builder
@Table(name = "teamCodingInfo")
@NoArgsConstructor //파라미터가 없는 기본생성자 생성
@AllArgsConstructor //모든 필드값을 파라미터로 받는 생성자 생성
public class TeamCodingInfoEntity {

    @Id //PK선언
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamSeq; //스터디 모임 Seq

    @Column(length = 200)
    @NotNull
    private String teamTitle; //스터디 모임 title

    @Column(length = 300)
    @NotNull
    private String teamContext; //스터디 모임 설명

    @Column(length = 20)
    @NotNull
    private Date teamStartDT; //스터디 시작일

    @Column(length = 20)
    @NotNull
    private Date teamEndDT; //스터디 종료일

    @Column(length = 20)
    @NotNull
    private int teamTotalCount; //스터디 현재 인원

    @Column(length = 20)
    @NotNull
    private int teamMaxCount; //스터디 참가 가능 최대 인원

    @Column(length = 200)
    @NotNull
    private String teamChatLink; //스터디 오픈채팅방 링크

    @OneToMany(mappedBy = "teamCodingInfo")
    private List<TeamCodingMemberInfoEntity> teamCodingMemberInfoEntityList = new ArrayList<>(); //teamMember 1:M 매핑

    @OneToMany(mappedBy = "teamCodingInfo")
    private List<TeamStudyInfoEntity> teamStudyInfoEntityList = new ArrayList<>(); //teamStudy 1:M 매핑

}
