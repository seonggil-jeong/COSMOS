package com.cosmos.userservice.dto;

import lombok.Data;

@Data
public class MailDto {

    private String to; //받는 사람 메일 주소
    private String subject; // 메일 제목
    private String text; // 메일 내용
}
