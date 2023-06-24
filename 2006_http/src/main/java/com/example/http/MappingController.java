package com.example.http;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.*;
import java.util.logging.Logger;

@Slf4j //log 변수 만들어준다 (원래 로그는 콘솔창)
@Controller
public class MappingController {
 //   private static final Logger logger
  //          = LoggerFactory.getLogger(MappingController.class);
    //->이 두 줄을 slf4j가 대신 해줌
    //System.out.println(""); -> logger.info(); logger.warn();
    //---- 로그관련 ----

    //path로 오는 'get'요청에 대해서 메소드를 실행하고 싶을 때
    @RequestMapping(
           value = "/path",
            method = RequestMethod.GET
    )
    public String getPath() {
        log.info("GET /path");
        return "index";
    }

    // '/path'로 오는 post 요청에 대해서 메소드를 실행하고 싶을 때
    @RequestMapping(
            value = "/path",
            method = RequestMethod.POST
    )
    public String postPath() {
        log.info("POST /path");
        return "index";
    }

    // '/path'로 오는 PUT 또는 DELETE 요청에 대해서
    //메소드를 실행하고 싶을 때
    @RequestMapping(
            value = "/path",
            method = { RequestMethod.PUT, RequestMethod.DELETE}
    )
    public String putOrDeletePath() {
        log.info("PUT or Delete /path");
        return "index";
    }

    //'/path'로 오는 POST요청이면서 JSON 데이터를 포함하는 요청에 대해하여
    //메소드를 실행하고 싶을 때
    @RequestMapping(
            value = "/path",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public String consumes() {
        log.info("POST /path Content-Type: application/json");
        return "index";
    }

    //'/path'로 오는 POST요청이면서
    //헤더에 x-likelion이라는 값이 hello로 지정되어 있을 때,
    //메소드를 실행하고 싶을 때
    //consumes와 유사하지만. 이건 매우 일반적인 사항
    //특정 헤더가 포함되어있는거 알릴때. 많이 사용하지는 않음
    @RequestMapping(
            value = "/path",
            method = RequestMethod.POST,
            headers = "x-likelion=hello"
    )
    public String headerWith() {
    log.info("POST /path with x-likelion=hello");
    return "index";
    }

    //'/path'로 오는 POST요청이면서
    //Query Parameter로 likelion이 hello로 할당되어 있을 때
    //메소드를 실행하고 있을 때
    @RequestMapping(
            value = "/path",
            method = RequestMethod.POST,
            params = "likelion=hello"
    )
    /* 이렇게 도 가능함. 차이는 지정하냐안하냐
    @PostMapping(
            value = "/path",
            params = "likelion=hello"
    )

     */
    public String params() {
        log.info("POST /path with parameter likelion");
        return "index";
    }
}
