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
}
