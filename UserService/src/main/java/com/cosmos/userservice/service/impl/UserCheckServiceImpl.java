package com.cosmos.userservice.service.impl;

import com.cosmos.userservice.exception.exceptions.NotFoundException;
import com.cosmos.userservice.jpa.UserRepository;
import com.cosmos.userservice.jpa.entity.UserEntity;
import com.cosmos.userservice.service.UserCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserCheckServiceImpl implements UserCheckService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    /**
     * 사용자 아이디를 가지고 유저 정보 조회, 유저 정보가 없다면 NOT_FOUND return then 사용자 정보가 있다면 비밀번호 일치 여부 확인
     *
     * @param userId
     * @param userPassword
     * @return true(비밀번호 일치) or false(비밀번호 불일치)
     */
    @Override
    public boolean checkUserPassword(String userId, String userPassword) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUserId(userId);

        if (userEntityOptional.isPresent()) {
            // 사용자 정보 있음
            String encPassword = userEntityOptional.get().getUserPassword(); // 인코딩된 사용자 비밀번호

            if (passwordEncoder.matches(userPassword, encPassword)) {
                // 사용자 비밀번호 일치 여부 확인 --> 일치

                return true;
            } else {
                // 사용자 비밀번호 불일치
                return false;
            }
        }
        // 사용자 정보가 없을 경우 Exception 실행
        throw new NotFoundException("사용자 정보를 찾을 수 없습니다");
    }



    @Override
    public boolean existsByUserId(String userId) {

        log.info("### UserCheckServiceImpl existsByUserId Start!!");

        Optional<UserEntity> userEntityOptional = userRepository.findByUserId(userId);

        // 중복된 아이디가 있으면 false 값 반환
        if (userEntityOptional.isPresent()) {
            log.info("### UserCheckServiceImpl 아이디 중복");
            return false;
        }
        log.info("### UserCheckServiceImpl 중복된 아이디 없음");
        // 중복된 아이다가 없으면 true 값 반환
        return true;
    }

    @Override
    public boolean existsByUserEmail(String userEmail) {

        log.info("### UserCheckServiceImpl existsByUserEmail Start!!");

        UserEntity userEntity = userRepository.findByUserEmail(userEmail);

        // 중복된 이메일이 없으면 true 값 반환
        if (userEntity == null) {
            log.info("### UserCheckServiceImpl 중복된 이메일 없음");
            return true;
        }
        // 중복된 이메일이 있으면 false 값 반환
        log.info("### UserCheckServiceImpl 이메일 중복");
        return false;
    }
}