package com.cosmos.teamcodingservice.jpa.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //Setter는 쓰지않는다. 값 타입은 될 수 있으면 불변 객체로 설계해야 하기때문에.
@Entity
@Builder
@Table(name = "teamCodingMemberInfo")
@NoArgsConstructor //파라미터가 없는 기본생성자 생성
@AllArgsConstructor //모든 필드값을 파라미터로 받는 생성자 생성
public class TeamCodingMemberInfoEntity {

    @Id //PK선언
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamMemberSeq; //팀멤버 seq

    @Column(length = 200)
    @NotNull
    private String userId; //유저 id

    @Column(length = 200)
    private String userNickname; //유저 닉네임(가변)

    @Column(length = 5)
    private int userGrant; //유저 권한(0 = 팀원, 1 = 팀장)

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private TeamCodingInfoEntity teamCodingInfo; //teamCoding 과 M:1 매핑

}
