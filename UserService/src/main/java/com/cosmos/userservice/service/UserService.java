package com.cosmos.userservice.service;

import com.cosmos.userservice.dto.UserDto;

public interface UserService {

    void findId(String userEmail);

    void findPassword(String userId, String userEmail);

    UserDto getUserInfoByUserId(String userId) throws Exception;

    void createUser(UserDto userDto);
}