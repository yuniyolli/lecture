package com.example.http.dto;

import lombok.Data;

import java.util.List;

//블로그 게시글
//게시륵 -제목
//게시륵 -내용
/*
제이슨 형태로 정의 (앞쪽 key가 field로 작용, 뒤의 kkk가 value
{
    "title": " kkk ",
    "content": "content"
}
 */
@Data
public class ArticleDto {
    private String title; //안녕하세요
    private String content;  //만나서 반갑습니다.
    private String writer;
    private List<String> contents;

}
