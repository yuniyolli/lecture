package com.example.http;

import com.example.http.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
@RestController
public class BodyController {
    /*
    // '/body'로 요청이 들어왔을 때,
    // ResponseDto 데이터를 표현한 JSON 응답을 반환하는 메소드
    @PostMapping("/body")
    //HTTP응답의 Body임을 나타내는 어노테이션 @ResponseBody
    public @ResponseBody ResponseDto body(
            @RequestBody ArticleDto requestDto
            ) {//ResponseDto반환하는걸 목적으로 함

        log.info(requestDto.toString());

        ResponseDto response = new ResponseDto();
        response.setStatus(200);
        response.setMessage("success");
        return response;

    }


     */
    //'/'body'로 ArticleDto를 표현한 JSON과 함께
    //요청이 들어왔을 때
    //responseDto를 표현한Json을 포함해 응답하는 메소드
    @PostMapping("/body")
   // @ResponseBody
    public ResponseDto body(
            @RequestBody
            ArticleDto dto
    ) {
        log.info("POST /body " + dto.toString());
        ResponseDto response = new ResponseDto();
        response.setMessage("success");
        response.setStatus(200);
        return response;
    }

    @PostMapping("/body-2")
   // @ResponseBody
    public ResponseDto body2(
            @RequestBody
            WriterDto dto
    ) {
        log.info("POST /body-2 " + dto.toString());
        ResponseDto response = new ResponseDto();
        response.setMessage("success");
        response.setStatus(200);
        return response;
    }

    @PostMapping("/body-3")
  //  @ResponseBody
    public ResponseDto body3(
            @RequestBody
           // ArticleDto dto
            ArticleWithCommentsDto dto
    ) {
        log.info("POST /body-3 " + dto.toString());
        ResponseDto response = new ResponseDto();
        response.setMessage("success");
        response.setStatus(200);
        return response;
    }

    @PostMapping("/body-4")
   // @ResponseBody
    public ResponseDto body4(
            @RequestBody
            ArticleComplexDto dto
    ) {
        log.info("POST /body-4 " + dto.toString());
        return new ResponseDto();
    }
    //@ResponseBody는 요청의 HTTP Body만 설정
    //to add 'Header'
    //or to select Status code
    //ResponseEntity<T> -> T means the type of DTO
    //compare this with the first body
    @PostMapping("/entity")
    public ResponseEntity<ResponseDto> entity(
            @RequestBody
            ArticleDto dto
    ) {
        log.info("POST /entity " + dto.toString());
        ResponseDto response = new ResponseDto();
        response.setMessage("success");
        response.setStatus(200);
        //1. 새 ResponseEntity 객체 생성
        //상태코드 지정해서 ResponseEntity 객체 그냥 쓰기
        ResponseEntity<ResponseDto> responseEntity
                = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
       // return responseEntity;

        //커스텀 헤더 만들고 함께 응답하기
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-likelion-custom", "Hello World!");
   //     return new ResponseEntity<>(
     //           response, headers, HttpStatus.ACCEPTED
       // );
//builder 사용하기
        return ResponseEntity
                .status(HttpStatus.CREATED)
                //자주 사용하는 것들 .ok(), .badRequest(), ...
                .header("x-likelion-one", "1")
                .headers(headers)
                .body(response);
    }
}
