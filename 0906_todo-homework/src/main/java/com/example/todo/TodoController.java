package com.example.todo;

import com.example.todo.model.TodoDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {

    private final TodoService todoService;

    public TodoController(
            TodoService todoService) {
        this.todoService = todoService;
    }


    @GetMapping("/create-view")
    public String createView() {
        return "home";
    }
    @PostMapping("/create")
    public String create(
            @RequestParam("content")
            String content
) {
    System.out.println(content);
    TodoDto todoDto
            = todoService.createTodo(content);
    System.out.println(todoDto.toString());
        // 새로운 TODO를 생성하는 컨트롤러 메소드
    return "redirect:/home";

        //throw new RuntimeException("TODO");
    }
    @GetMapping("/todo")
    public String todoView() {
        return "create";
    }
        @GetMapping("/home")
    public String home(Model model){
        model.addAttribute(
                "todoList",
                todoService.readTodoAll()
        );
        return "create";


    }



    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model
    ) {
        System.out.println(id);
        model.addAttribute(
                "todo",
                todoService.readTodo(id)
        );
        return "todo";
    }
    /*
    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model
    ){
        System.out.println(id);
        model.addAttribute(
                "todo",
                todoService.readTodo(id)
        );
        return "read";
    }

     */
    @GetMapping("/{ild}/edit-view")
    public String editView(
            @PathVariable("id") Long id, Model model
    ){
        TodoDto dto = todoService.readTodo(id);
        model.addAttribute("todo",dto );
        return"create";
    }
@PostMapping("/{id}/edit")
    public String update(
            @PathVariable("id") Long id,
            @RequestParam("content") String content
) {
    TodoDto todoDto = todoService.updateTodo(id, content);
    return String.format("redirect:/%s",id);
        // TODO의 done 상태를 변경하는 메소드
       // throw new RuntimeException("TODO");
    }
@PostMapping("/{id}/delete")
    public String delete(
            @PathVariable("id")
            Long id
) {
    todoService.deleteTodo(id);
    return "create";
    // TODO를 삭제하는 메소드
        //throw new RuntimeException("TODO");
    }
}