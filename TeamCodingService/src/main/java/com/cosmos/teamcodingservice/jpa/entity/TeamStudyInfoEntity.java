package com.cosmos.teamcodingservice.jpa.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "teamStudyInfo")
@NoArgsConstructor //파라미터가 없는 기본생성자 생성
@AllArgsConstructor //모든 필드값을 파라미터로 받는 생성자 생성
public class TeamStudyInfoEntity {

    @Id //PK선언
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamStudySeq; //teamStudy seq

    @Column(length = 20)
    private Date teamStudyDate; //스터디 모임 일자

    @Column(length = 300)
    private String teamStudyContents; //특정 일자 스터디 설명

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private TeamCodingInfoEntity teamCodingInfo;

    @OneToMany(mappedBy = "teamStudyInfo")
    private List<TeamStudyDetailEntity> teamStudyDetailEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "teamStudyInfo")
    private List<TeamStudyUserInfoEntity> teamStudyUserInfoEntityList = new ArrayList<>();
}
