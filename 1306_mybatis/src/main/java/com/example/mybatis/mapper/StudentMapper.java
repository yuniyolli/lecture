package com.example.mybatis.mapper;

import com.example.mybatis.mapper.provider.StudentProvider;
import com.example.mybatis.model.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper // MyBatis가 Mapper가 붙은 클래스를 데이터베이스 통신에 사용할 준비를 해준다
public interface StudentMapper {
    // INSERT INTO students(name, age, phone, email)
    //VALUE (?, ?, ?, ?);
    //insert만 하고 받아올 반환값은 없음
    @Insert("INSERT INTO students (name, age, phone, email)\n" +
            "VALUES (#{name}, #{age}, #{phone}, #{email})")
    // 자동으로 student.getName, student.email등 사용해줌. preparedinjection? 같은거 사용에도 대비
    void insertStudent(Student student);

    @Insert("INSERT INTO students(name, age, phone, email)\n" +
            "VALUES(#{name}, #{age}, #{phone}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertStudentKeys(Student student);

    // SELECT * FROM students; 를 실행할 메소드 만들기
    // 복수개의 Students 를 반환하게 하는 return 타입-> List<Student>
    @Select("SELECT * FROM students")
    List<Student> selectStudentAll();


    @Select("SELECT * FROM students WHERE id = #{id}")
    Student selectStudent(Long id);

    //update
    @Update("UPDATE students SET " +
            "name = #{name}, " +
            "age = #{age}, " +
            "phone = #{phone}, " +
            "email = #{email}" +
            "WHERE id = #{id}")
    void updateStudent(Student student);

    //delete
    @Delete("DELETE FROM students " +
            "WHERE id = #{id}")
    void deleteStudent(Long id);


    //dml SELECT,INSERT,UPDATE, DELETE 얘들 다 있어!
    //

    // select with optional
    @Select("SELECT * FROM students WHERE id = #{id}")
    Optional<Student> selectStudentOptional(Long id);

    // dynamic sql
    @SelectProvider(type = StudentProvider.class, method = "findByFields")
    List<Student> findByFields(Student student);

}
