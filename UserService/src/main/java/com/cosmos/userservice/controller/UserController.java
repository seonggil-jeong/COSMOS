package com.cosmos.userservice.controller;

import com.cosmos.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/findId")
    public ResponseEntity<String> findId(@RequestBody Map<String, Object> param) {

        String userEmail = (String) param.get("userEmail");

        userService.findId(userEmail);

        return ResponseEntity.status(HttpStatus.OK).body("가입하신 이메일로 아이디를 전송했습니다.");
    }

    @PostMapping("/findPassword")
    public ResponseEntity<String> findPassword(@RequestBody Map<String, Object> param) {

        String userId = (String) param.get("userId");
        String userEmail = (String) param.get("userEmail");

        userService.findPassword(userId, userEmail);

        return ResponseEntity.status(HttpStatus.OK).body("가입하신 이메일로 변경된 임시 비밀번호를 전송했습니다.");
    }
}
