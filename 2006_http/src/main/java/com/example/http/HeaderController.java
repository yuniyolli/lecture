package com.example.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class HeaderController {
    //'header-one'으로 들어온 HTTP요청에 대하여
    //헤더 중 'x-likelion'이라는 헤더의 값을 인자로 전달받고 싶을 때
    @PostMapping("/header-one")
    public String getHeader(
            @RequestHeader("x-likelion") String header) {
        log.info("POST /header-one header:" + header);
        return "index";
    }

    //'header-one' 으로 들어온 HTTP요청에 대하여
    //헤더중 'x-likelion'이 있으면 할당, 없으면 null로 받고 싶을 때
    @PostMapping("header-optional")
    public String getHeaderOptional(
            @RequestHeader(
                    value = "x-likelion", required = false
            ) String header //Optional<String> 활용가능
    ) {
        log.info("POST /header-optional header: " + header);
        return "index";
    }

    //'header-type'으로 들어온 HTTP요청에 대하여
    //헤더 중 'x-likelion-int'가 있으면 Integer에 할당
    @PostMapping("/header-type")
    public String getHeaderInteger(
            @RequestHeader(value = "x-likelion-int")
            Integer header
    ) {
        log.info("POST /header-type header: " + header);
        return "index";
    }

    //'header-all'로 들어온 HTTP요청의
    //모든 해더를 확인하고 싶을 때 (3ways to check out all the headers
    @PostMapping("/header-all")
    public String getHeaderAll(
            //1.
            //@RequestHeader //모든 헤더를 다 가져올 때 (인자값을 안넣으면 됨
            //Map<String, String> headerMap //맵에 요청에 포함된 모든 헤더를 포함해서 줌

            //2.

            //@RequestHeader
           // MultiValueMap<String, String> headerMap



            //3.
            @RequestHeader
            HttpHeaders headerMap

    ) {
        log.info("POST /header-all");
        /*
        for (Map.Entry<String, String> entry: headerMap.entrySet()) {
            log.info(String.format(
                    "%s: %s", entry.getKey(), entry.getValue()));
        }

         */
        //1.
        /*
        for (Map.Entry<String, String> entry: headerMap.entrySet()) {
            log.info(String.format(
                    "%s: %s", entry.getKey(), entry.getValue()));
        }
    return "index";
    }

         */

        //2. 3.
        for (Map.Entry<String, List<String>> entry : headerMap.entrySet()) {
            log.info(String.format(
                    "%s: %s", entry.getKey(), entry.getValue()));
        }
        return "index";
    }
}
