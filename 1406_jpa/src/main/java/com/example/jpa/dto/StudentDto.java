package com.example.jpa.dto;

import com.example.jpa.entities.StudentEntity;
import lombok.Data;

//자바객체, 롬복 활용하기 -> getter, setter...
@Data
public class StudentDto {
    //사용자가 봐야하는 정보, 안봐도 되는정보를 dto에서 구현
    //dto 정의

    private Long id; //Entity.id가 될 것
    private String name; //Entity.name
    private String email; // Entity.email

//정적 팩토리 메소드 패턴
    //static factory method pattern
    public static StudentDto
    fromEntity(StudentEntity studentEntity) {
    StudentDto dto = new StudentDto();
    dto.setId(studentEntity.getId());
    dto.setName(studentEntity.getName());
    dto.setEmail(studentEntity.getEmail());
    return dto;
    //dto가 어떻게 만들어질지 스스로 정할 수 있음

        }

    }


