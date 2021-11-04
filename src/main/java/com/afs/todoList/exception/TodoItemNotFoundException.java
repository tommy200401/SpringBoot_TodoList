package com.afs.todoList.exception;

public class TodoItemNotFoundException extends RuntimeException{
    public TodoItemNotFoundException(){
        super("Todo item not found.");
    }
}
