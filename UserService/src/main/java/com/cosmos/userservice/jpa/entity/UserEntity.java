package com.cosmos.userservice.jpa.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter //Setter는 쓰지않는다. 값 타입은 될 수 있으면 불변 객체로 설계해야 하기때문에.
@Entity
@Builder
@Table(name = "userInfo")
@NoArgsConstructor //파라미터가 없는 기본생성자 생성
@AllArgsConstructor //모든 필드값을 파라미터로 받는 생성자 생성
public class UserEntity {
    @Id //PK선언
    //GenerationType.IDENTITY = 기본키생성을 DB에 위임해서 id값을 따로 선언하지않아도
    //DB가 자동으로 AUTO_INCREMENT를 하여 기본키를 생성한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userSeq;


    //nullable=false 말고 @NotNull 쓰는 이유 :
    //@NotNull을 쓰면, 데이터베이스에 SQL 쿼리를 보내기 전에 예외가 발생
    @Column(length = 50, unique = true)
    @NotNull
    private String userId;

    @Column(length = 50, unique = true)
    @NotNull
    private String userEmail;

    @Column(length = 50)
    @NotNull
    private String userPassword;

    @Column(length = 50)
    @NotNull
    private String userName;

    @Column(length = 50)
    @NotNull
    private String userNickname;

    @CreationTimestamp //시간 자동입력
    private Timestamp userRegDate;

    @Column(length = 1)
    private String userGender;

    @Column(length = 5)
    @NotNull
    private int userAge;

    @Column(length = 50)
    private String userLocation;

    @Column
    private int userRank;

    @Column
    private int userStatus;

    @OneToMany(mappedBy = "user")
    private List<UserStackEntity> userStackEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserLinkEntity> userLinkEntityList = new ArrayList<>();

}