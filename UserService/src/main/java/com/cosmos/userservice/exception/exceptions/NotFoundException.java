package com.cosmos.userservice.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // 이 예외는 NOT_FOUND 로 처리 함
public class NotFoundException extends RuntimeException{ // RuntimeException 을 변경해서 구현

    public NotFoundException(String message) {
        super(message);
    }
}
