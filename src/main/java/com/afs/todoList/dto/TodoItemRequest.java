package com.afs.todoList.dto;

public class TodoItemRequest {
    private String text;
    private Boolean done;

    public TodoItemRequest() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
