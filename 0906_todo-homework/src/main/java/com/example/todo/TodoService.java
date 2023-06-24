package com.example.todo;

import com.example.todo.model.TodoDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private final List<TodoDto> todoList = new ArrayList<>();
    private Long nextId = 1L;
/*
    public TodoService() {
        createTodo("readBook");
        createTodo("Swimming");
        createTodo("Homework");
    }

 */



    public TodoDto createTodo(String content) {
        TodoDto newTodo = new TodoDto (
                nextId, content
        );
        nextId++;
        todoList.add(newTodo);
        return newTodo;
    }

    public List<TodoDto> readTodoAll() {
        return todoList;
    }
public TodoDto readTodo(Long id) {
        for(TodoDto todoDto : todoList) {
            if(todoDto.getId().equals(id))
                return todoDto;
        }
        return null;
}

public TodoDto updateTodo(Long id, String content) {
        int target = -1;
    for (int i = 0; i < todoList.size(); i++) {
        if(todoList.get(i).getId().equals(id)) {
            target = i;
            break;
        }
    }
    if (target != -1) {
        todoList.get(target).setContent(content);
        return todoList.get(target);
    }
    else return null;
}

public boolean deleteTodo(Long id) {
        int target = -1;
    for (int i = 0; i < todoList.size(); i++) {
        if(todoList.get(i).getId().equals(id)) {
            target = i;
            break;
        }
    }
    if (target != -1) {
        todoList.remove(target);
        return true;
    }else return false;
}

    }

