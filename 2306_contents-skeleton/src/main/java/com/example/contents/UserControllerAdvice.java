package com.example.contents;

import com.example.contents.dto.ResponseDto;
import com.example.contents.exceptions.Status400Exception;
import com.example.contents.exceptions.Status404Exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@RestControllerAdvice
//컨트롤러는 컨트롤러 내부에서 발생한 오류에만 반응
//@RestControllerAdvice는 각 Controller에 나뉘어진 ExceptionHandler메소드를 모으는 용도
public class UserControllerAdvice {
    @ExceptionHandler(Status400Exception.class)
    public ResponseEntity<ResponseDto> handleIllegalState(Status400Exception exception) {
        ResponseDto response = new ResponseDto();
        //response.setMessage("UserControllerAdvice에서 처리한 예외입니다. ");
        response.setMessage(exception.getMessage());
        return ResponseEntity
                .badRequest()
                .body(response);
        //Status400Exeption을 상속받는 모든 예외들에 대하여
        //400 코드를 발생시킨다
    }

    @ExceptionHandler(Status404Exception.class)
    public ResponseEntity<ResponseDto> handle404(
            Status404Exception exception
    ) {
        ResponseDto response = new ResponseDto();
        response.setMessage(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }
}
