package com.cosmos.userservice.controller;

import com.cosmos.userservice.dto.UserDto;
import com.cosmos.userservice.service.UserService;
import com.cosmos.userservice.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/findId")
    public ResponseEntity<String> findId(@RequestParam String userEmail) {
        userService.findId(userEmail);
        return ResponseEntity.status(HttpStatus.OK).body("가입하신 이메일로 아이디를 전송했습니다.");
    }

    @PostMapping("/findPassword")
    public ResponseEntity<String> findPassword(@RequestParam String userId, String userEmail) {
        userService.findPassword(userId, userEmail);
        return ResponseEntity.status(HttpStatus.OK).body("가입하신 이메일로 변경된 임시 비밀번호를 전송했습니다.");
    }


    /**
     * Headers(Token 정보) 를 사용하여 userId 추출 후 userId를 사용하여 사용자 정보를 조회 및 return
     * @param headers (token)
     * @return UserDto
     * @throws Exception
     */
    @GetMapping("/users")
    public ResponseEntity<UserDto> findUserInfoByUserId(@RequestHeader HttpHeaders headers) throws Exception {
        log.info(this.getClass().getName() + ".findUserInfoByUserId Start!");

        String userId = TokenUtil.getUserIdByToken(headers);

        UserDto userDto = userService.getUserInfoByUserId(userId);

        log.info(this.getClass().getName() + ".findUserInfoByUserId End!");

        return ResponseEntity.ok().body(userDto);
    }

}
