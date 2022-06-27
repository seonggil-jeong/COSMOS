package com.cosmos.userservice.exception;

import com.cosmos.userservice.exception.exceptions.BadRequestException;
import com.cosmos.userservice.exception.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice // 모든 Controller 실행 시 같이 실행
@Slf4j

/**
 * 모든 Controller 실행 시 같이 실행됨 & throw new {Exception Name} 로 호출
 */

public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class) // 기본적인 Exception 이 발생 시 이 오류를 리턴
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception exception, WebRequest webRequest) {

        log.info("EXCEPTION : " + exception); // Error 정보를 Front 에 전송하지 않음 Log 처리 or DB 저장
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
        // 각 Date, timestamp, details 에 매칭 ( ExceptionResponse(Date, String, String) )


        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class) // Not Found Exception 발생 시 호출
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(Exception exception, WebRequest webRequest) {

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));


        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class) // Bad Request Exception 발생 시 호출
    public final ResponseEntity<ExceptionResponse> handleBadRequestException(Exception exception, WebRequest webRequest) {

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));


        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
