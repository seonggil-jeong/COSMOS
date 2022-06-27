package com.cosmos.userservice.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // 이 예외는 NOT_FOUND 로 처리 함
public class BadRequestException extends RuntimeException{ // RuntimeException 을 변경해서 구현

    public BadRequestException(String message) {
        super(message);
    }
}
