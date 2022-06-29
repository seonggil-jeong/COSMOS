package com.cosmos.userservice.controller;

import com.cosmos.userservice.dto.MailDto;
import com.cosmos.userservice.util.MailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final MailUtil mailUtil;

    @RequestMapping("/test")
    public String sendEmail() {

        MailDto mailDto = new MailDto();
        mailDto.setTo("ojh9607@naver.com");
        mailDto.setSubject("테스트 메일입니다.");
        mailDto.setText("안녕하세요.");

        mailUtil.sendMail(mailDto);

        return "ok";
    }
}
