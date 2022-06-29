package com.cosmos.userservice.controller;

import com.cosmos.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 아이디 찾기(이메일 전송)
    @PostMapping("/findId")
    public ResponseEntity<String> findId(@RequestParam String userEmail) {
        log.info("### findId.Controller Start !");

        userService.findId(userEmail);

        log.info("### findId.Controller End !");
        return ResponseEntity.status(HttpStatus.OK).body("가입하신 이메일로 아이디를 전송했습니다.");
    }

    // 비밀번호 찾기(이메일 전송)
    @PostMapping("/findPassword")
    public ResponseEntity<String> findPassword(@RequestParam String userId, String userEmail) {
        log.info("### findPassword.Controller Start !");

        userService.findPassword(userId, userEmail);

        log.info("### findPassword.Controller End !");
        return ResponseEntity.status(HttpStatus.OK).body("가입하신 이메일로 변경된 임시 비밀번호를 전송했습니다.");
    }


}
