package com.cosmos.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private Date timeStamp; // Error 발생 시간
    private String message; // Error 내용
    private String details; // Error 내용 상세
}
