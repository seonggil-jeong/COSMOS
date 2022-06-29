package com.cosmos.userservice.jpa;

import com.cosmos.userservice.dto.UserDto;
import com.cosmos.userservice.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    // 사용자 아이디를 사용하여 USER_INFO 조회 / Optional = Null 처리
    Optional<UserEntity> findByUserId(String userId);

    // 아이디 찾기 로직(이메일 기준 회원 정보 가져오기)
    UserEntity findByUserEmail(String userEmail);


}