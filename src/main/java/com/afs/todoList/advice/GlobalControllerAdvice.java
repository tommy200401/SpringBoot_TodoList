package com.afs.todoList.advice;

import com.afs.todoList.exception.TodoItemNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler({TodoItemNotFoundException.class})
    public ErrorResponse notFoundExceptionsHandler(Exception exception){
        return new ErrorResponse(404, exception.getMessage());
    }
}
