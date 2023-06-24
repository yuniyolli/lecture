package com.example.lombok;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class StudentDto { //Dto라고 이름짓는 객체들: 데이터를 담기위한 객체
    private Long id;
    private String name;
    private String email;

    //Constructor
    //Getter Setter
    //toString
    //equals

}
