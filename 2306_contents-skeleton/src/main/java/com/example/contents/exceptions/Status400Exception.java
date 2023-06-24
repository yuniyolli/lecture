package com.example.contents.exceptions;

public abstract class Status400Exception extends RuntimeException{
//constructor
    public Status400Exception(String message) {
        super(message);
        //runtimeException을 상속받는 Status400Exception
        //400이 일어날 수 있는 상(user name중복, email 오류, 전화번호 오류, 자기소개 길때)
    }
}
