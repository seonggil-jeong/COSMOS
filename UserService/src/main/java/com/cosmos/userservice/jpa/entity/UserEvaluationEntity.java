package com.cosmos.userservice.jpa.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"user"})
@Table(name = "userEvaluation")
public class UserEvaluationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userEvaluationSeq; // Seq

    @Column(length = 50)
    @NotNull
    private String toUserId; // 평가 받는 사람 Id

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private UserEntity user;
}
