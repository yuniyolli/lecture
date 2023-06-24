package com.example.todo.model;

public class TodoDto {
    private Long id;
    private String content;
    //private Boolean done;


    public TodoDto(Long id, String content) {
        this.id = id;
        this.content = content;
        //this.done = done;
    }

    public TodoDto() {

    }

    @Override
    public String toString() {
        return "TodoDto{" +
                "id=" + id +
                ", content='" + content + '\'' +
               // ", done=" + done +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //public Boolean getDone() {
     //   return done;
    //}

    //public void setDone(Boolean done) {
   //     this.done = done;
   // }
}
