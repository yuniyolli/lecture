package com.example.crud;

import com.example.crud.model.StudentDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@Service
public class StudentService {
    //복수의 StudentDto를 담는 변수
    private final List<StudentDto> studentList = new ArrayList<>();
    private Long nextId = 1L;

    /* 임시 자료저장용
    public StudentService() {
        createStudent("alex", "alex@gmail.com");
        createStudent("brad", "brade@gmail.com");
        createStudent("chad", "chad@gmail.com");
    }

     */

    //새로운 StduentDto를 생성하는 메소드
    public StudentDto createStudent(String name, String email) {
        StudentDto newStudent = new StudentDto(
                nextId, name, email
        );
        nextId++;
        studentList.add(newStudent);
        return newStudent;
    }

    public List<StudentDto> readStudentAll() {
        return studentList;
    }

    //Service에서 단일 StudentDto를 주는 메소드를 만들겁니다
    //이때 이 메소드가 받을 인자는 무엇일까요? long id
    public StudentDto readStudent(Long id) {
        //Todo
        //여기는 그냥 JAVA코드가 들어감. id 기준으로 검색해서 찾으면 됨
        for (StudentDto studentDto : studentList) {
            if (studentDto.getId().equals(id))
                return studentDto;
        }

        return null;
    }

    public StudentDto updateStudent(Long id, String name, String email) {
        int target = -1;
        for (int i = 0; i < studentList.size(); i++){
            if (studentList.get(i).getId().equals(id)) {
                target = i;
                break;
            }
        }
        if (target != -1) {
            studentList.get(target).setName(name);
            studentList.get(target).setEmail(email);
            return studentList.get(target);
        }
        else return null;
    }

    //updateStudent stream으로 표현하기
    /*
    public StudentDto updateStudent(Long id, String name, String email) {
        return studentList
                .stream()
                .filter(studentDto -> studentDto.getId().equals(id))
                .peek(studentDto -> {
                    studentDto.setName(name);
                    studentDto.setEmail(email);
                })
                .findFirst()
                .orElse(null);
    }

     */

    public boolean deleteStudent(Long id) {
        int target = -1;
        //학생의 리스트를 살펴보며
        for (int i = 0; i < studentList.size(); i++) {
            //대상을 선정한다
            if (studentList.get(i).getId().equals(id)) {
                target = i;
                break;

            }
        }
        if (target != -1) {
            studentList.remove(target);
            return true;
        } else return false;
        }
/*
    public boolean deleteStduent(Long id) {
        OptionalInt idx = IntStream
                .range(0, studentList.size())
                .filter(i -> studentList.get(i).getId().equals(id))
                .findFirst();
        if (idx.isPresent()) {
            studentList.remove(idx.getAsInt());
            return true;
    }
        return false;
    }

 */
}

