package com.cosmos.userservice.service;

public interface UserCheckService {
    /**
     * 사용자 비밀번호, 아이디, 이메일 유효성 검사 및 사용 가능 여부 Check
     */

    boolean checkUserPassword(String userId, String userPassword);
}
