package com.cosmos.userservice.controller;


import com.cosmos.userservice.dto.UserStackDto;
import com.cosmos.userservice.service.UserStackService;
import com.cosmos.userservice.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserStackController {
    private final UserStackService userStackService;

    /**
     * Header 에 담겨있는 token 정보에서 userId 를 추출 후 정보 조회 실행
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
}
