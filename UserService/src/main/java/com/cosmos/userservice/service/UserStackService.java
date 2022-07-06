package com.cosmos.userservice.service;

import com.cosmos.userservice.dto.UserStackDto;

import java.util.List;

public interface UserStackService {


    /**
     * 사용자 아이디 값을 사용하여 사용자 기술 스택 조회
     * @param userId
     * @return userStackDtoList
     * @throws Exception
     */
    List<UserStackDto> getStackInfoByUserId(String userId) throws Exception;

    /**
     * 사용자 아이디 및 StackDtoList List<Integer(StackSeq)> 를 받아 deleteAll and saveAll
     * 기존의 Stack 정보를 deleteAll 후 update 된 Stack 정보를 저장
     * @param userStackDtoList
     * @param userId
     * @return int ( 1 or 0 )
     * @throws Exception
     */
    int updateStackInfoByUserId(List<UserStackDto> userStackDtoList, String userId) throws Exception;
}
