package com.example.crud;

import com.example.crud.model.StudentDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {
    // StudentService를 Controller에서 사용
    private final StudentService studentService;

    public StudentController(
            StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/create-view")
    public String createView() {
        return "create";
    }

    @PostMapping("/create")
    public String create(
            @RequestParam("name")
            String name,
            @RequestParam("email")
            String email
    ) {
        System.out.println(name);
        System.out.println(email);
        StudentDto studentDto
                = studentService.createStudent(name, email);
        System.out.println(studentDto.toString());
       // System.out.println(newStudent);
        //return "redirect:/create-view";
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute(
                "studentList",
                studentService.readStudentAll()
        );
        return "home";
    }

    @GetMapping("/{id}")
    public String read(

            @PathVariable("id") Long id,
            Model model
    ){
      //  studentService.readStudent(id);
        System.out.println(id);
        model.addAttribute(
                "student",
                studentService.readStudent(id)
        );
        return "read";
    }



    @GetMapping("/{id}/update-view")
    public String updateView(
            @PathVariable("id") Long id,
            Model model
          ) {
        StudentDto dto = studentService.readStudent(id);
        model.addAttribute("student", dto);
        return "update";
    }

    @PostMapping("/{id}/update")
    public String update(
            //Todo StudentController.readOne()를 참조
            //Long id
            @PathVariable("id") Long id,
            //Todo StudentController.create()를 참조
            @RequestParam("name") String name,
            @RequestParam("email") String email
            //String name
            //String email
    ) {
        //service 활용하기
        StudentDto studentDto = studentService.updateStudent(id, name, email);

        //상세보기 페이지로 redirect
        return String.format("redirect:/%s", id);

    }

        // deleteView 메소드 만들기
        @GetMapping("/{id}/delete-view")
        public String deleteView(
                @PathVariable("id") Long id,
                Model model
                ){
            StudentDto studentDto
                    = studentService.readStudent(id);
            model.addAttribute("student", studentDto);
            return "delete";
        }
        //GetMapping을 써서...
        //Long id는 어떻게...
        //studentDto 를 가지고...
        //return




    @PostMapping("/{id}/delete")
    public String delete(
            @PathVariable("id")
            Long id
    ) {
        studentService.deleteStudent(id);
        //update때는 데이터가 남아있지만
        //delete는 돌아갈 상세보기가 없다.
        //그래서 홈으로 돌아간다.
        return "redirect:/home";
    }



}
