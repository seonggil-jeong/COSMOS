package com.cosmos.userservice.controller;


import com.cosmos.userservice.dto.UserStackDto;
import com.cosmos.userservice.service.UserStackService;
import com.cosmos.userservice.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserStackController {
    private final UserStackService userStackService;

    /**
     * Header 에 담겨있는 token 정보에서 userId 를 추출 후 정보 조회 실행
     *
     * @param headers
     * @return HashMap
     * @throws Exception
     */
    @GetMapping("/user/stack")
    public ResponseEntity<Map<String, List<UserStackDto>>> getUserStackInfoByUserId(@RequestHeader HttpHeaders headers) throws Exception {
        log.debug(this.getClass().getName() + ".getUserStackInfoByUserId Start!");

        Map<String, List<UserStackDto>> rMap = new HashMap<>();


        String userId = TokenUtil.getUserIdByToken(headers);
        List<UserStackDto> userStackDtoList = userStackService.getStackInfoByUserId(userId);

        // JSON 형식으로 값 return 을 위해 Map 에 담아서 전송
        rMap.put("response", userStackDtoList);

        log.debug(this.getClass().getName() + ".getUserStackInfoByUserId End!");


        return ResponseEntity.status(HttpStatus.OK).body(rMap);
    }

    @PostMapping("/user/stack")
    public ResponseEntity<String> updateUserStackInfoByUserId(@RequestHeader HttpHeaders headers, @RequestBody List<UserStackDto> userStackDtoList)
            throws Exception {
        log.debug(this.getClass().getName() + ".updateUserStackInfoByUserId Start!");

        String userId = TokenUtil.getUserIdByToken(headers);

        if (userStackDtoList.isEmpty()) {
            userStackDtoList = new ArrayList<>();
        }

        int res = userStackService.updateStackInfoByUserId(userStackDtoList, userId);

        log.debug(this.getClass().getName() + ".updateUserStackInfoByUserId End!");

        if (res == 1) {
            // 값이 있다면 Create code (201)
            return ResponseEntity.status(HttpStatus.CREATED).body("변경 성공");
        } else {
            // 값이 없었다면 Ok code (200)
            return ResponseEntity.status(HttpStatus.OK).body("변경 성공");
        }

    }
}
